function [Vx,Vy] = demo_optical_flow(folder_name,frame_number_1,frame_number_2)

% -------------------------------- %
% DO NOT CHANGE THIS FUNCTION !!!! %
% -------------------------------- %

% This is just a helper function to test your code

% This is a demo to show the optical flow in quiver plot
% Examples of using it:
% demo_optical_flow('Backyard',10,11)
% demo_optical_flow('Backyard',10) -> it automatically runs  demo_optical_flow('Backyard',10,11)
% demo_optical_flow('Backyard') -> it runs demo_optical_flow('Backyard',7,8)

if(nargin == 0)
    folder_name = 'Backyard';
    frame_number_1 = 7;
    frame_number_2 = frame_number_1 + 1;
elseif(nargin == 1)
    frame_number_1 = 7;
    frame_number_2 = frame_number_1 + 1;
elseif(nargin ==2)
    frame_number_2 = frame_number_1 + 1;
end

addpath(folder_name);

frame_1 = read_image(folder_name,frame_number_1);
original1 = read_image_original(folder_name,frame_number_1);
frame_2 = read_image(folder_name,frame_number_2);
original2 = read_image_original(folder_name,frame_number_2);
[Vx,Vy] = compute_LK_optical_flow(frame_1,frame_2);

plotflow(Vx,Vy,original1);
title('Quiver plot');

end
            
function plotflow(Vx,Vy,original1)

% -------------------------------- %
% DO NOT CHANGE THIS FUNCTION !!!! %
% -------------------------------- %

s = size(Vx);
step = max(s)/50; % You can change this if you want a finer grid
[X, Y] = meshgrid(1:step:s(2), s(1):-step:1);
u = interp2(Vx, X, Y);
v = interp2(Vy, X, Y);

imagesc(original1)
hold on
quiver(X, Y, u, v, 1, 'y', 'LineWidth', 1);
axis image;


end

function I = read_image(folder_name,index)

% -------------------------------- %
% DO NOT CHANGE THIS FUNCTION !!!! %
% -------------------------------- %

if(index < 10)
    I = imread(fullfile(folder_name,strcat('image_smoothed_',num2str(index),'.png')));
    % Used to be 'frame0'
else
    I = imread(fullfile(folder_name,strcat('image_smoothed_',num2str(index),'.png')));
    % Used to be 'frame'
end

end
function I = read_image_original(folder_name,index)

% -------------------------------- %
% DO NOT CHANGE THIS FUNCTION !!!! %
% -------------------------------- %

    if (index < 10)
        I = imread(fullfile(folder_name,strcat('frame',num2str(index),'.png')));
        % Used to be 'frame0'
    else
        I = imread(fullfile(folder_name,strcat('frame',num2str(index),'.png')));
        % Used to be 'frame'
    end

end


function [motionFieldX, motionFieldY] = compute_LK_optical_flow(frame_1, frame_2)
    % Variable
    sigma = 2;
    sigma_1d = 2;
    windowSize = 20; 

    % Convert images into doubles
    img1_d = double(frame_1);
    img2_d = double(frame_2);

    % Computer 2D gaussian
    gauss1 = imgaussfilt(img1_d, sigma);
    gauss2 = imgaussfilt(img2_d, sigma);

    % Take the graident of the 2D gaussian
    [Ix, Iy] = gradient(gauss1);

    % Create a 1D gaussian and normalize it
    gaussian_1d = (1 / (sqrt(2*pi) * sigma_1d)) * exp(-(windowSize * 2).^2 / (2 * sigma_1d^2));
    gaussian_1d = gaussian_1d/sum(gaussian_1d); %normalize

    % Get the Transformation/Difference between 2 images
    diffArr = gauss1 - gauss2;

    % Set Motion Field to 0
    motionFieldX = zeros(size(img1_d));
    motionFieldY = zeros(size(img1_d));
    
    for y = 1 + windowSize : size(img1_d, 2) - windowSize

        for x = 1 + windowSize : size(img1_d, 1) - windowSize

            % Grab 2D window from graidents according to variables
            window_grad_x = Ix(x - windowSize : x + windowSize, y - windowSize : y + windowSize);
            window_grad_y = Iy(x - windowSize : x + windowSize, y - windowSize : y + windowSize);
            
            % Get the second motion matrix by multiplying:
            % grad(I) * grad(I)^T
            % And then taking the sum
            grad_I = [window_grad_x, window_grad_y];
            sndMotionArr = sum(grad_I * transpose(grad_I), 'all');

            % Convolve each row of the window gradients with the 1D gaussian
            for i = 1 : windowSize*2+1
            
                window_grad_x(i, :) = conv(window_grad_x(i, :), gaussian_1d, 'same');
                window_grad_y(i, :) = conv(window_grad_y(i, :), gaussian_1d, 'same');
            
            end
            
            % Get the 2D window from the difference array
            diffWindow = diffArr(x - windowSize : x + windowSize, y - windowSize : y + windowSize);

            % Get the time array
            timeArr = [sum((window_grad_x.*diffWindow), "all"); 
                       sum((window_grad_y.*diffWindow), "all")];
            
            % Find the Motion field by inverse of sndMotion * timeDiff
            motion_field = inv(sndMotionArr) * timeArr;
            
            motionFieldX(x, y) = motion_field(1);
            motionFieldY(x, y) = motion_field(2);
        end
    end
end