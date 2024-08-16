%% Setup read image

im = imread("images\IMG_2216.jpg");
im_g = im2gray(im);
im_d = im2double(im_g);

imshow(im);

%% Real-World Axis Setup

% Picked points manually from the image to form my Real-World coordinates
rw_origin = [3231 1331];
rw_xAxis = [3903 884];
rw_yAxis = [3041 2129];
rw_zAxis = [2377 800];

%% Image-Axis Point Selection

points = [
    3170 993; 
    3106 675;
    3404 1468;
    3549 1561;
    2864 1432;
    2550 1517;
];

%% Show axis and points

% Show original image
imshow(im);
hold on
% Shows the axes
line([rw_origin(1) rw_xAxis(1)], [rw_origin(2) rw_xAxis(2)], 'Color', 'y', 'LineWidth', 4);
line([rw_origin(1) rw_yAxis(1)], [rw_origin(2) rw_yAxis(2)], 'Color', 'c', 'LineWidth', 4);
line([rw_origin(1) rw_zAxis(1)], [rw_origin(2) rw_zAxis(2)], 'Color', 'm', 'LineWidth', 4);

% Shows the points selected
for i = 1 : length(points)
    plot(points(i, 1), points(i, 2), Marker="o", MarkerSize=12, LineWidth=2);
end

legend(["X Axis", "Y Axis", "Z Axis", "Point 1", "Point 2", "Point 3", "Point 4", "Point 5", "Point 6"]);
hold off

%% Estimation for points in the Real-World axes

% let 1mm be 4 pixels
% One square on a rubik's cube is 18x18mm
% In format: (x, y, z)
rw_points = [
    72 0 72;
    144 0 144;
    72 72 0;
    144 144 0;
    0 72 72;
    0 144 144;
];

%% Normalize points 

% Calculate Average
mean_pic = mean(points, 1);
mean_rw = mean(rw_points, 1);

% Calculate Standard Deviation
sigma_pic = 0;
sigma_rw = 0;

for i = 1 : length(points)

    sigma_pic = sigma_pic + (points(i, 1) - mean_pic(1))^2 + (points(i, 2) - mean_pic(2))^2;

    sigma_rw = sigma_rw + (rw_points(i, 1) - mean_rw(1))^2 ...
                        + (rw_points(i, 2) - mean_rw(2))^2 ...
                        + (rw_points(i, 3) - mean_rw(3))^2;

end

sigma_pic = sigma_pic / (2 * length(points));
sigma_pic = sqrt(sigma_pic);

sigma_rw = sigma_rw / (3 * length(rw_points));
sigma_rw = sqrt(sigma_rw);

% Apply normalization

norm_points = points;
norm_rw_points = rw_points;

for i = 1 : length(points)

    % Normalize the picture coods
    norm_points(i, 1) = (norm_points(i, 1) - mean_pic(1)) / sigma_pic;
    norm_points(i, 2) = (norm_points(i, 2) - mean_pic(2)) / sigma_pic;
    
    norm_rw_points(i, 1) = (norm_rw_points(i, 1) - mean_rw(1)) / sigma_rw;
    norm_rw_points(i, 2) = (norm_rw_points(i, 2) - mean_rw(2)) / sigma_rw;
    norm_rw_points(i, 3) = (norm_rw_points(i, 3) - mean_rw(3)) / sigma_rw;

end

% Create the M matrices for reversing Normalized P later
M1 = ...
[
    1/sigma_pic 0 0;
    0 1/sigma_pic 0;
    0 0 1;
] ...
* ...
[
    1 0 -1*mean_pic(1);
    0 1 -1*mean_pic(2);
    0 0 1;
];

M2 = [
    1/sigma_rw 0 0 0;
    0 1/sigma_rw 0 0;
    0 0 1/sigma_rw 0;
    0 0 0 1
] ...
* ...
[
    1 0 0 -1*mean_rw(1);
    0 1 0 -1*mean_rw(2);
    0 0 1 -1*mean_rw(3);
    0 0 0 1;
];

%% Estimate camera projection matrix

neg_norm_points = norm_points.*-1;

% Form the matrix based on the points taken
A = ...
[
    norm_rw_points(1, :) 1 0 0 0 0 (neg_norm_points(1, 1)*norm_rw_points(1, 1)) (neg_norm_points(1, 1)*norm_rw_points(1, 2)) (neg_norm_points(1, 1)*norm_rw_points(1, 3)) neg_norm_points(1, 1);
    0 0 0 0 norm_rw_points(1, :) 1 (neg_norm_points(1, 2)*norm_rw_points(1, 1)) (neg_norm_points(1, 2)*norm_rw_points(1, 2)) (neg_norm_points(1, 2)*norm_rw_points(1, 3)) neg_norm_points(1, 2);

    norm_rw_points(2, :) 1 0 0 0 0 (neg_norm_points(2, 1)*norm_rw_points(2, 1)) (neg_norm_points(2, 1)*norm_rw_points(2, 2)) (neg_norm_points(2, 1)*norm_rw_points(2, 3)) neg_norm_points(2, 1);
    0 0 0 0 norm_rw_points(2, :) 1 (neg_norm_points(2, 2)*norm_rw_points(2, 1)) (neg_norm_points(2, 2)*norm_rw_points(2, 2)) (neg_norm_points(2, 2)*norm_rw_points(2, 3)) neg_norm_points(2, 2);

    norm_rw_points(3, :) 1 0 0 0 0 (neg_norm_points(3, 1)*norm_rw_points(3, 1)) (neg_norm_points(3, 1)*norm_rw_points(3, 2)) (neg_norm_points(3, 1)*norm_rw_points(3, 3)) neg_norm_points(3, 1);
    0 0 0 0 norm_rw_points(3, :) 1 (neg_norm_points(3, 2)*norm_rw_points(3, 1)) (neg_norm_points(3, 2)*norm_rw_points(3, 2)) (neg_norm_points(3, 2)*norm_rw_points(3, 3)) neg_norm_points(3, 2);

    norm_rw_points(4, :) 1 0 0 0 0 (neg_norm_points(4, 1)*norm_rw_points(4, 1)) (neg_norm_points(4, 1)*norm_rw_points(4, 2)) (neg_norm_points(4, 1)*norm_rw_points(4, 3)) neg_norm_points(4, 1);
    0 0 0 0 norm_rw_points(4, :) 1 (neg_norm_points(4, 2)*norm_rw_points(4, 1)) (neg_norm_points(4, 2)*norm_rw_points(4, 2)) (neg_norm_points(4, 2)*norm_rw_points(4, 3)) neg_norm_points(4, 2);

    norm_rw_points(5, :) 1 0 0 0 0 (neg_norm_points(5, 1)*norm_rw_points(5, 1)) (neg_norm_points(5, 1)*norm_rw_points(5, 2)) (neg_norm_points(5, 1)*norm_rw_points(5, 3)) neg_norm_points(5, 1);
    0 0 0 0 norm_rw_points(5, :) 1 (neg_norm_points(5, 2)*norm_rw_points(5, 1)) (neg_norm_points(5, 2)*norm_rw_points(5, 2)) (neg_norm_points(5, 2)*norm_rw_points(5, 3)) neg_norm_points(5, 2);

    norm_rw_points(6, :) 1 0 0 0 0 (neg_norm_points(6, 1)*norm_rw_points(6, 1)) (neg_norm_points(6, 1)*norm_rw_points(6, 2)) (neg_norm_points(6, 1)*norm_rw_points(6, 3)) neg_norm_points(6, 1);
    0 0 0 0 norm_rw_points(6, :) 1 (neg_norm_points(6, 2)*norm_rw_points(6, 1)) (neg_norm_points(6, 2)*norm_rw_points(6, 2)) (neg_norm_points(6, 2)*norm_rw_points(6, 3)) neg_norm_points(6, 2);
];

% Take the smallest eigenvalue from singular value decomposition
% Convert the values into the P matrix
[~, ~, M] = svd(A.' * A);
M = M(:, end);
P = reshape(M, [], 3);
P = transpose(P);

% Check if the values are close 0
verify = A * M;

% Un-normalize P (Inv(M1) * P * M2)
P = M1 \ P * M2;

%% Testing projection

% Picked points based on known positions in real-world
test_rw_points = ...
[
    144 0 72 1;
    72 0 144 1;
    144 72 0 1;
    72 144 0 1;
    0 72 144 1;
    0 144 72 1;
];

% The points picked on the estimates of where they are in the picture
test_pic_points = ...
[
    3402 846;
    2870 822;
    3614 1326;
    3318 1750;
    2586 1242;
    2822 1706;
];

proj_diff = zeros(6, 2);

figure();
imshow(im);
hold on

% Calculate the image's position based on real-world points
% Draw circle around that point and then draw a line from the circle to the
% actual point.
for i = 1 : length(test_rw_points)

    temp = test_rw_points(i, :);
    temp = temp.';
    
    res = P * temp;
    res = res./res(3);
    res = res(1:2).';

    plot(res(1), res(2), Marker="o", MarkerSize=12, LineWidth=2, Color='c');
    line([res(1) test_pic_points(i, 1)], [res(2) test_pic_points(i, 2)], "LineWidth", 2, "Color", 'm');
    
    proj_diff(i, :) = res - test_pic_points(i, :);

end

hold off

%% Get projection error

% Calculate the mean of the projection error
proj_mean = mean(proj_diff);

% Calculate the stddev of the projection error
proj_std = 0;

for i = 1 : length(proj_diff)

    proj_std = proj_std ...
             + (proj_diff(i, 1) - proj_mean(1))^2 ...
             + (proj_diff(i, 2) - proj_mean(2))^2;

end

proj_std = sigma_pic / (2 * length(proj_diff));
proj_std = sqrt(proj_std);



