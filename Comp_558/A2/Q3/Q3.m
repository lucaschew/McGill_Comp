%% Q3) Lucas-Kanade Optical Flow

for fi = 7 : 13

    im1 = imread("Backyard\frame" + fi + ".png");
    im2 = imread("Backyard\frame" + (fi+1) + ".png");
    
    im1_d = im2gray(im2double(im1));
    im2_d = im2gray(im2double(im2));
    
    % Parameters
    
    sigma = 4;
    vectorSpacing = 10;
    magnification = 10;
    windowSize = 5;
    [sizeY, sizeX] = size(im1_d);
    
    % Solve the equation

    gauss2D = imgaussfilt(im1_d, sigma);
    [im_x_2d, im_y_2d] = gradient(gauss2D);

    for x = 1 : vectorSpacing : length(im1_d(1, :)) - windowSize*2
    
        for y = 1 : vectorSpacing : length(im1_d(:, 1)) - windowSize*2   

            % Motion Field = Inverse of original position array * The amount moved (t)
            
            % Inverse of Original Position Array with guass filter of sigma
            % Applying 2D Gaussian to image

            window_gx = im_x_2d(y: y+(windowSize*2), x: x+(windowSize*2)); 
            window_gy = im_y_2d(y: y+(windowSize*2), x: x+(windowSize*2));

            
            originalPosArr = [ sum(window_gx.^2, "all"), sum(window_gx.*window_gy, "all");
                               sum(window_gx.*window_gy, "all"), sum(window_gy.^2, "all")];
            
            originalPosArr = inv(originalPosArr);
            
            % The amount moved (t)
            imDiff = im1_d - im2_d;
            
            % Applying 1D gaussian to each row
            gauss1D = gaussmf(sizeX, [sigma, 0]);
            gauss1D = gauss1D/sum(gauss1D);
            
            im_gauss1d = im1_d;
            
            for i = 1 : sizeY
            
                im_gauss1d(i, :) = im_gauss1d(i, :)*gauss1D;
            
            end
            
            [im_x_1d, im_y_1d] = gradient(gauss2D);
            
            motionField_x = zeros(size(im1_d));
            motionField_y = zeros(size(im1_d));
            
            % Find motion for each pixel
            pointDiff = imDiff(y, x);
    
            motionArr = [sum((im_x_1d.*pointDiff), "all"); 
                             sum((im_y_1d.*pointDiff), "all")];
    
            motionField_x(y, x) = motionArr(1);
            motionField_y(y, x) = motionArr(2);
    
        end
    
    end
    
    % Create the smaller motion field 
    small_MFX = motionField_x(1:vectorSpacing:end, 1:vectorSpacing:end).*magnification;
    small_MFY = motionField_y(1:vectorSpacing:end, 1:vectorSpacing:end).*magnification;
    
    [X,Y] = meshgrid(1:vectorSpacing:sizeX, 1:vectorSpacing:sizeY);
    
    % Display original image with motion field on top
    imshow(im1);
    hold on;
    quiver(X, Y, small_MFX, small_MFY, 'y');
    hold off;
    saveas(gcf, "Backyard_Results\frame" + fi + ".png");

end

