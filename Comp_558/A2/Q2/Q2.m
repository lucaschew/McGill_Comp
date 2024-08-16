%% Setup Image

Iorig1 = imread('medial1.png');
Iorig2 = imread('medial2.png');

Iorig1_d = im2double(rgb2gray(Iorig1));
Iorig2_d = im2double(rgb2gray(Iorig2));

%% Q2a) Gaussian Pyramid

octaveLevel = [0, 1, 2, 3, 4, 5];

gPy1 = createGaussianPyramid(Iorig1_d, 1);
subplot(3,2,1);
imshow(gPy1);
title("Gaussian Pyramid Octave Level 1σ")

gPy2 = createGaussianPyramid(Iorig1_d, 2);
subplot(3,2,2);
imshow(gPy2);
title("Gaussian Pyramid Octave Level 2σ")

gPy4 = createGaussianPyramid(Iorig1_d, 4);
subplot(3,2,3);
imshow(gPy4);
title("Gaussian Pyramid Octave Level 4σ")

gPy8 = createGaussianPyramid(Iorig1_d, 8);
subplot(3,2,4);
imshow(gPy8);
title("Gaussian Pyramid Octave Level 8σ")

gPy16 = createGaussianPyramid(Iorig1_d, 16);
subplot(3,2,5);
imshow(gPy16);
title("Gaussian Pyramid Octave Level 16σ")

gPy32 = createGaussianPyramid(Iorig1_d, 32);
subplot(3,2,6);
imshow(gPy32);
title("Gaussian Pyramid Octave Level 32σ")

figure();

%% Q2b) Sift Scales and Difference of Gaussians

m = 4;

[gradPy, Dog, localExtremas] = getDogPyramid(Iorig1_d, [0, 1, 2, 3, 4, 5], m);

figureCounter = 1;
sigmaCounter = 0;

for i = 1 : length(Dog)

    %subplot(2,2,figureCounter);
    %imshow(Dog{:, i}.*20);
    title("2^{" + (sigmaCounter+0.25) + "} - 2^{" + sigmaCounter + "}");

    figureCounter = figureCounter + 1;
    sigmaCounter = sigmaCounter+0.25;

    if (figureCounter == 5)
        figureCounter = 1;
        %figure();
    end

end

%figure();


%% Q2c) Finding local extremas

% findExtrema(currentLevel, above, below, stddev)

extremaImage = Iorig1;

% Color and show spots with circles 
for i = 1 : length(localExtremas) 

    p = localExtremas(i, :);

    if (p(3) == 0)
        continue;
    end

    level = 2^floor(p(3));

    color = 'red';
    if (level == 2)
        color = 'green';
    elseif (level == 4)
        color = 'blue';
    elseif (level == 8)
        color = 'cyan';
    elseif (level == 16)
        color = 'magenta';
    elseif (level == 32)
        color = 'yellow';
    end

    extremaImage = insertShape(extremaImage, "circle", [(p(2)*level) (p(1)*level) (level/2)], "ShapeColor", color);

end

imshow(extremaImage);
figure();


%% Q2d) Evaluate Quality of Sift keypoints

% Get Rotated Image's Gaussians, DOG Pyramid, and Extremas
[gradPy2, Dog2, localExtremas2] = getDogPyramid(Iorig2_d, [0, 1, 2, 3, 4, 5], 4);

% Show the points SIFT points on the rotated image
%
extremaImage2 = Iorig2;
% Color and show spots with circles 
for i = 1 : length(localExtremas2) 

    p = localExtremas2(i, :);

    if (p(3) == 0)
        continue;
    end

    level = 2^floor(p(3));

    color = 'red';
    if (level == 2)
        color = 'green';
    elseif (level == 4)
        color = 'blue';
    elseif (level == 8)
        color = 'cyan';
    elseif (level == 16)
        color = 'magenta';
    elseif (level == 32)
        color = 'yellow';
    end
    extremaImage2 = insertShape(extremaImage2, "circle", [(p(2)*level) (p(1)*level) (level/2)], "ShapeColor", color);
end
imshow(extremaImage2);
figure();
%

% For each keypoint, find its scale level and create an mxm window.
% Compute the histogram of ht local grad with b # of bins

histogramKeyPoints1 = getHistogramKeyPoints(localExtremas, gradPy);

% Now do the same for rotated image

histogramKeyPoints2 = getHistogramKeyPoints(localExtremas2, gradPy2);

% use xcorr to find the closest matching one and remove both and show it

indexPairs = matchFeatures(histogramKeyPoints1, histogramKeyPoints2, Unique=true, MaxRatio=0.99);

% Get the matched points according to the index
% Multiply the points by the scale to make it back to 512 x 512
matchedPoints1 = histogramKeyPoints1(indexPairs(:,1), 1:2).*2.^floor(histogramKeyPoints1(indexPairs(:,1), 3));
matchedPoints2 = histogramKeyPoints2(indexPairs(:,2), 1:2).*2.^floor(histogramKeyPoints2(indexPairs(:,2), 3));

% Flip the points (I believe my x and y values were swapped)
matchedPoints1 = flip(matchedPoints1, 2);
matchedPoints2 = flip(matchedPoints2, 2);

showMatchedFeatures(extremaImage,extremaImage2,matchedPoints1,matchedPoints2);
legend("matched points 1","matched points 2");
figure();

%% Functions

% For Q2a)
function img = createGaussianPyramid(image, std)

    shrink = 1/std;

    % Filter by stddev
    gauss = imgaussfilt(image, std);
    % Multiply image by the gauss
    img = image.*gauss;
    % Resize
    img = imresize(img, shrink, "bilinear");

end

% For Q2c)
function points = findExtrema(currentLevel, above, below, stddev)

    % Size of current image 
    [sizeX, sizeY] = size(currentLevel);

    % If it is empty, just set it to a zero array
    % If its the wrong size, resize it
    if (size(above, 1) == 0)
        above = zeros(sizeX, sizeY);
    elseif (size(above, 1) ~= sizeX)
        above = imresize(above, (sizeX/size(above, 1)));
    end

    if (size(below, 1) == 0)
        below = zeros(sizeX, sizeY);
    elseif (size(below, 1) ~= sizeX)
        below = imresize(below, (sizeX/size(below, 1)));
    end

    % Threshold
    threshold = 0.02;

    points = zeros(1, 3);
    counter = 2;

    area = zeros(3,3,3);

    for x = 2 : sizeX-1

        for y = 2 : sizeY-1
                    
            % If it is above the threshold, check if it is the extrema
            if (abs(currentLevel(x, y)) >= threshold) 
        
                area(:,:,1) = below(x-1:x+1, y-1:y+1);
                area(:,:,2) = currentLevel(x-1:x+1, y-1:y+1);
                area(:,:,3) = above(x-1:x+1, y-1:y+1);
                area(2,2,2) = 0;
        
                area = abs(area);
        
                % Take only the graduate peaks and dips
                % Does not take when something spikes in the opposite dir.
                if (abs(currentLevel(x, y)) >= max(area) * ((0.2 * (1/2^(stddev)) + 1)))
                    points(counter, :) = [x, y, stddev];
                    counter = counter+1;
                end
            end
        end
    end

    points = points(2:end, :);

end

% For Q2d)
function [gradients, diffOfGradients, extremas] = getDogPyramid(image, stddevs, levels)

    gradients = cell(1, (length(stddevs)-1) * levels + 1);
    diffOfGradients = cell(1, (length(stddevs)-1) * levels);

    gradCounter = 2;
    diffGradCounter = 1;

    prevImage = imgaussfilt(image, 1);
    gradients{:, 1} = prevImage;

    for i = 1/levels : 1/levels : length(stddevs)-1/levels
        
        std = (2^i);
    
        % Resize by i+k and shrink to the original stddev
        gaussNew = imgaussfilt(image, std);
        gaussShrink = imresize(gaussNew, 1/2^(floor(i)), "bilinear");

        diff = gaussShrink - prevImage;
        prevImage = gaussShrink;

        %imshow(diff.*20);
    
        if (mod(i, 1) == 0.75)
    
            prevImage = imresize(gaussNew, 1/2^(ceil(i)), "bilinear");
    
        end

        gradients{:, gradCounter} = prevImage;
        diffOfGradients{:, diffGradCounter} = diff;

        gradCounter = gradCounter + 1;
        diffGradCounter = diffGradCounter + 1;

    end

    extremas = [];
    levelCount = (1/levels);

    for i = 2 : length(diffOfGradients) - 1

        level = levelCount;
        levelCount = levelCount + (1/levels);

        if (i == 1)
            extremas = findExtrema(diffOfGradients{:, i}, diffOfGradients{:, i+1}, [], level);
        elseif (i == length(diffOfGradients))
            tempList = findExtrema(diffOfGradients{:, i}, [], diffOfGradients{:, i-1}, level);
            extremas = cat(1, extremas, tempList);
        else
            tempList = findExtrema(diffOfGradients{:, i}, diffOfGradients{:, i+1}, diffOfGradients{:, i-1}, level);
            extremas = cat(1, extremas, tempList);
        end

    end

end

function histogramKeyPoints = getHistogramKeyPoints(extremaArr, gradientArr)

    numOfBins = 36;

    histogramKeyPoints =  zeros(length(extremaArr), 3 + numOfBins);

    for i = 1 : length(extremaArr)

        point = extremaArr(i, :);
    
        % Skips if the point is null/empty
        if (point(3) == 0)
            continue;
        end
    
        % As m = 4, 1/m = 0.25
        % Skip the first gradient
        gradNumber = (point(3) / 0.25) + 1;
    
        % The m x m around the value
        gradRad = 3;
    
        gradArr = gradientArr{:, gradNumber};
        gradArrLength = length(gradArr);
    
        % Get the m x m area in the original gradient
        focusedArr = gradArr( max(min(point(1)-gradRad, gradArrLength), 1) : ...
                              max(min(point(1)+gradRad, gradArrLength), 1) ...
                              , max(min(point(2)-gradRad, gradArrLength), 1) : ...
                              max(min(point(2)+gradRad, gradArrLength), 1));
    
        % Get orientation and magnitude
        [mag, gradOrientation] = imgradient(focusedArr);
    
        % Get the bings
        [histCounts, histBins] = imhist(gradOrientation, numOfBins);
    
        % Rotate into a single row
        histCounts = histCounts.';
    
        % Shift the bins until the max value is at the beginning.
        while (histCounts(1) ~= max(histCounts))
    
            histCounts = [histCounts(2:end), histCounts(1)];
    
        end

        histogramKeyPoints(i, :) = cat(2, extremaArr(i, :), histCounts);
    
    end

end

