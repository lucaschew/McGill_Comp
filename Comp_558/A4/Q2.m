%% Camera Caliibration

% K Left
cam0=[1733.74 0 792.27; 0 1733.74 541.89; 0 0 1];
% K Right
cam1=[1733.74 0 792.27; 0 1733.74 541.89; 0 0 1];
%doffs=0;
% Baseline
Tx = 536.62;
%width=1920;
%height=1080;
%ndisp=170;
%vmin=55;
%vmax=142;
%fmx
fmx = 3060;
fmy = 3060;


%% Image Setup

% Read images
img_L = imread("images\middlebury_left.png");
img_R = imread("images\middlebury_right.png");

imgd_L = im2double(im2gray(img_L));
imgd_R = im2double(im2gray(img_R));

%% Disparity Calculations

tic;
disparity = calculateDisparity(imgd_L, imgd_R);
toc;

%% Graphing

% Normalize and Smooth the image more
depthMap = normalizeSmoothImage(disparity, fmx, Tx);

% Scalar value adjustment for better image clarity
im = depthMap./1.5;

figure(1);
imshow(im);

%% Matlab Example
%{
disparityRange = [0 128];
disparityMap = disparitySGM(imgd_L, imgd_R,'DisparityRange',disparityRange,'UniquenessThreshold',1);

figure;
imshow(disparityMap,disparityRange);
title('Disparity Map');
colormap jet;
colorbar;
%}

%% Graph Error Explanation
%
yTest = 760;

leftIntensity = imgd_L(:, yTest);
rightIntensity = imgd_R(:, yTest);

figure(2);
hold on;

plot(leftIntensity);
plot(rightIntensity);
xlabel("Pixel");
ylabel("Intensity");
title("Intensity Graph at Y = 760px")

hold off;
%

%% Functions

function image = normalizeSmoothImage(im, fmx, Tx)

    % Get the absolute value of the disparity and then smooth it
    aDisparity = abs(im);
    aDisparity=medfilt2(aDisparity, [5 5]);
    
    % Add it by a flat value to make it easier to see
    aDisparity = aDisparity + max(aDisparity, [], 'all');
    
    % Calculate the Depth Map
    Z = (fmx * Tx)./ aDisparity;
    
    % Set all inf to 0 
    Z(Z==inf) = 0;
    
    % Normalize the diparity map
    maxZ = max(Z, [], "all");
    
    image = Z./maxZ;

end

function disparity = calculateDisparity(img_L, img_R)

    % How large the search block is
    blockRad = 2;
    % Search distance on each side
    searchDistance = 130;

    imageSize = size(img_R);

    disparity = zeros(imageSize);

    for y = blockRad+1 : imageSize(1) - blockRad

        %y

        % Set counter to keep track of last selected pixel
        prevIndex = imageSize(2) - blockRad;

        for x =  imageSize(2) - blockRad : -1 : blockRad+1
            
            % Get the right image's block
            blockR = img_L(y-blockRad : y+blockRad, x-blockRad : x+blockRad);

            counter = 1;
            
            % Calculate start and end points for the search distance
            zStart = min(prevIndex, x + searchDistance);
            zEnd = max(blockRad + 1, x - searchDistance);

            % Storage array for all the values created
            sumDiff = ones(zEnd - zStart, 1);
            
            for z = zStart : -1 : zEnd

                % Get the left image's block
                blockL = img_R((y - blockRad) : (y + blockRad), (z - blockRad) : (z + blockRad) );

                % Get the SAD and store it
                imageDiff = abs(blockL(:) - blockR(:));
                sumDiff(counter) = sum(imageDiff, "all");     
                counter = counter + 1;

            end
            
            % Get the smallest value's value and index
            [val, index] = min(sumDiff);
            
            % If it isn't close enough, skip it
            % Data Cost
            if isempty(val) || abs(val) > 0.75

               disparity(y, x) = 0;

            else

                disparity(y, x) = (zStart - index - x);
                prevIndex = zStart - index;

            end

            % Smoothing Cost
            if (y > blockRad+1 && x < imageSize(2) - blockRad)
                disparity(y, x) = disparity(y, x) ...
                                  + 0.1*(disparity(y, x) - disparity(y-1, x)) ...
                                  + 0.1*(disparity(y, x) - disparity(y, x+1));
            end

        end
    end
end
