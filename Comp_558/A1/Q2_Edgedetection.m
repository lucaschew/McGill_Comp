% Q3a) 

stddev = 2;
range = 1;

% Create 2D Default Gaussian
arr = zeros(2*stddev + 1, 2*stddev + 1);

for i = 1 : 2*stddev + 1

    x = linspace((-1 * range) * stddev, range * stddev, 2*stddev + 1);
    y = 1/(2*pi*stddev)*exp(-(x).^2/(2*stddev^2));
    arr(i, :) = y / sum(y);

end

% Visualize 2D Gaussian
% surf(arr);

arr_dy2 = arr;

for i = 1 : 2*stddev + 1

    dy1 = conv(arr(i, :), [1 0 -1], 'same');
    arr_dy2(i, :) = conv(dy1, [1 0 -1], 'same');

end

surf(arr_dy2);
colorbar;
title("2D 2nd Derivative Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");


%%
% Q3b) Create 2D Tapered Y-Axis Gaussian

stddev_y = stddev * 2;

normalized_1D_Gaussian = 1/(2*pi*stddev_y)*exp(-(linspace((-1 * range) * stddev, range * stddev, 2*stddev + 1)).^2/(2*stddev_y^2));
normalized_1D_Gaussian = normalized_1D_Gaussian / sum(normalized_1D_Gaussian);

y_Gaussian = arr;

for i = 1 : stddev*2 + 1

    y_Gaussian(:, i) = times(y_Gaussian(:, i).', normalized_1D_Gaussian);

end

surf(y_Gaussian);
colorbar;
hold on;
%surf(arr);
title("2D Tapered Y-Axis Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");
hold off;


%%
% Q3c) Create Theta-Rotated Gaussian

% I created a function at the bottom to help translate the points, to allow for
% cleaner code

% 0° Rotation
[x_r0, y_r0] = rotateGaussian(stddev, 0);

surf(x_r0, y_r0, y_Gaussian);
colorbar;
title("2D 0° Rotated Tapered Y-Axis Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");

% 45° Rotation
[x_r45, y_r45] = rotateGaussian(stddev, 45);

surf(x_r45, y_r45, y_Gaussian);
colorbar;
title("2D 45° Rotated Tapered Y-Axis Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");

% 90° Rotation
[x_r90, y_r90] = rotateGaussian(stddev, 90);

surf(x_r90, y_r90, y_Gaussian);
colorbar;
title("2D 90° Rotated Tapered Y-Axis Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");

% 135° Rotation
[x_r135, y_r135] = rotateGaussian(stddev, 135);

surf(x_r135, y_r135, y_Gaussian);
colorbar;
title("2D 135° Rotated Tapered Y-Axis Gaussian");
xlabel("x-axis");
ylabel("y-axis");
zlabel("z-axis");


%%

img = imread()

%%
% Helper Function which is the implementation for Q3c and used in Q3d
function [rotatedX, rotatedY] = rotateGaussian(stddev, degrees)

    xy_arr = zeros(2, (2*stddev + 1)*(2*stddev + 1));

    counter = 1;
    
    for i = 1 : stddev*2 + 1
    
        for z = 1: stddev*2 + 1
    
            xy_arr(:, counter) = [i; z];
            counter = counter + 1;
    
        end
    
    end
    
    centerIndex = ceil((2*stddev + 1)/2);
    center = repmat([centerIndex,; centerIndex], 1, length(xy_arr));
    
    R = [cosd(degrees) sind(degrees); -sind(degrees) cosd(degrees)];
    
    rotated45 = R*(xy_arr - center) + center;
    
    x = rotated45(1, :);
    y = rotated45(2, :);
    
    rotatedX = reshape(x, 2*stddev + 1, 2*stddev + 1);
    rotatedY = reshape(y, 2*stddev + 1, 2*stddev + 1);

end
