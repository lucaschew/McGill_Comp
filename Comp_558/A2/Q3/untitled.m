%% Q3) Lucas-Kanade Optical Flow

for fi = 7 : 13

    im1 = imread("Backyard\frame" + fi + ".png");
    im2 = imread("Backyard\frame" + (fi+1) + ".png");
    
    im1_d = im2gray(im2double(im1));
    im2_d = im2gray(im2double(im2));
    
    motionField_x = zeros(size(im1_d));
    motionField_y = zeros(size(im1_d));

    % Parameters
    
    sigma = 4;
    vectorSpacing = 10;
    [sizeY, sizeX] = size(im1_d);
    windowRad = 20;

    % The amount moved (t)
    imDiff = im1_d - im2_d;

    % Create 1D Gaussian
    gauss1D_x = (1:windowRad*2+1);
    gauss1D_y = (1/(sqrt(2*pi)*sigma))*exp(-((gauss1D_x).^2)/(2*(sigma.^2))); 
    gauss1D_y = gauss1D_y./sum(gauss1D_y);

    % Create 2D Gaussian
    gauss2D = imgaussfilt(im1_d, sigma);
    [im_x_2d, im_y_2d] = gradient(gauss2D);

    for y = 1 + windowRad : vectorSpacing : length(im1_d(1, :)) - windowRad
    
        for x = 1 + windowRad : vectorSpacing : length(im1_d(:, 1)) - windowRad

            windowArr_x_gauss2D = im_x_2d(x-windowRad : x+windowRad, y-windowRad : y+windowRad);
            windowArr_y_gauss2D = im_y_2d(x-windowRad : x+windowRad, y-windowRad : y+windowRad);
            
            % Get the second moment values
            dxdx = windowArr_x_gauss2D^2;
            dxdy = windowArr_x_gauss2D*windowArr_y_gauss2D; 
            dydy = windowArr_y_gauss2D^2;

            % Subtract the mean from the values
            %dxdx = dxdx - mean(dxdx, 'all');
            %dxdy = dxdy - mean(dxdy, 'all');
            %dydy = dydy - mean(dydy, 'all');

            %dxdx = dxdx*100;
            %dxdy = dxdy*100;
            %dydy = dydy*100;

            %imshow(dxdx*100);
            %imshow(dxdy*100);
            %imshow(dydy*100);

            sndMotionArr = [ sum(dxdx, "all"), sum(dxdy, "all");
                               sum(dxdy, "all"), sum(dydy, "all")];

            sndMotionArr_T = transpose(sndMotionArr);
            
            % 1D Gauss to Right side of Equation
            
            im_gauss1d = im1_d(x-windowRad : x+windowRad, y-windowRad : y+windowRad);
            
            [im_x_1d, im_y_1d] = gradient(im_gauss1d);

                        for i = 1 : windowRad*2+1
            
                im_gauss1d(i, :) = conv(im_gauss1d(i, :), gauss1D_y, 'same');
            
            end
            
            % Find motion for each pixel
    
            diffArr = imDiff(x-windowRad : x+windowRad, y-windowRad : y+windowRad);
    
            timeArr = [sum((im_x_1d.*diffArr), "all"); 
                       sum((im_y_1d.*diffArr), "all")];

            % Inv of snd Movement arr * time arr
            motionArr = pinv(sndMotionArr * sndMotionArr_T) * sndMotionArr_T * timeArr;
    
            motionField_x(x, y) = motionArr(1);
            motionField_y(x, y) = motionArr(2);
    
        end
    
    end
    
    % Create the smaller motion field 
    small_MFX = motionField_x(1+windowRad:vectorSpacing:end-windowRad, 1+windowRad:vectorSpacing:end-windowRad);
    small_MFY = motionField_y(1+windowRad:vectorSpacing:end-windowRad, 1+windowRad:vectorSpacing:end-windowRad);
    
    [X,Y] = meshgrid(1+windowRad:vectorSpacing:sizeX-windowRad, 1+windowRad:vectorSpacing:sizeY-windowRad);
    
    % Display original image with motion field on top
    imshow(im1_d);
    hold on;
    quiver(X, Y, small_MFX, small_MFY, 'r');
    hold off;
    saveas(gcf, "Backyard_Results2\frame" + fi + ".png");

end

