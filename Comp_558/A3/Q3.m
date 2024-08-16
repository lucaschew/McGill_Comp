%% Variables

P1 = [
    3.05115553196587,	1.41365645372546,	-2.43204819501826,	1754.82078390321;
    -0.793062141742458,	3.06505664567849,	-1.48882825025383,	728.926644465165;
    0.000330810940726087,	0.000590456667285870,	1.77400810858770e-05,	0.543167655220744;
];

K = [
  3640, 0, 2016;
  0, 3640, 1512;
  0, 0, 1;
];

im2 = imread("images\IMG_2217.jpg");
im3 = imread("images\IMG_2218.jpg");

im2_d = im2gray(im2double(im2));
im3_d = im2gray(im2double(im3));

%imshow(im2);
%imshow(im3);

% In format: (x, y, z)
rw_points = [
    72 0 72;
    144 0 144;
    72 72 0;
    144 144 0;
    0 72 72;
    0 144 144;
];

%% Calculate P for Image 2

% In format of Origin, X Axis, Y Axis, Z Axis
rw_originXYZ_points_2 = [
    2942 1362;
    3930 990;
    2834 2174;
    2322 710;
];

image_points_2 = [
    3050 1010;
    3146 682;
    3234 1530;
    3502 1678;
    2694 1434;
    2478 1466;
];

% The true/false parameter changes whether the image's axes figure will be shown
[P2, Verify2] = getCameraProjectionModel(rw_originXYZ_points_2, image_points_2, rw_points, false, im2);

%% Calculate P for Image 3

rw_originXYZ_points_3 = [
    2518 1440;
    3689 1223;
    2465 2246;
    2204 740;
];

image_points_3 = [
    2775 1110;
    3019 824;
    2870 1657;
    3216 1876;
    2378 1486;
    2274 1520;
];

figure();
% The true/false parameter changes whether the image's axes figure will be shown
[P3, Verify3] = getCameraProjectionModel(rw_originXYZ_points_3, image_points_3, rw_points, false, im3);

%% Get R and T

[R1, T1, Camera_Loc1] = getCameraLocation(P1, K);
[R2, T2, Camera_Loc2] = getCameraLocation(P2, K);
[R3, T3, Camera_Loc3] = getCameraLocation(P3, K);

%% Plot

% 18mm = 72 pixels
% There are 3 squares per a side
% Therefore, the cube should be 216pixels
cube = [
    0 0 0;
    216 0 0;
    216 216 0;
    0 216 0;
    0 0 216;
    216 0 216;
    216 216 216;
    0 216 216;
];

% How to join the points together
connectors = [4 8 5 1 4; 1 5 6 2 1; 2 6 7 3 2; 3 7 8 4 3; 5 8 7 6 5; 1 4 3 2 1]';
cubeCords_X = cube(:,1);
cubeCords_Y = cube(:,2);
cubeCords_Z = cube(:,3);
% Invert Z axis as that is how it is in the photos
cubeCords_Z = cubeCords_Z.*-1;

plot3(cubeCords_X(connectors), cubeCords_Y(connectors), cubeCords_Z(connectors), 'y');
xlabel('x'); 
ylabel('y');
zlabel('z');

% Get points of the camera rectacle
cameraSize = size(im2_d);

[cameraSquare1] = getCameraLensTry(T1, R1, cameraSize);
[cameraSquare2] = getCameraLensTry(T2, R2, cameraSize);
[cameraSquare3] = getCameraLensTry(T3, R3, cameraSize);

hold on;

% Draw a line from the picture's origin to the real-world's origin
line([cameraSquare1(1, 1) 0], [cameraSquare1(1, 2) 0], [cameraSquare1(1, 3) 0], "Color", 'r');
line([cameraSquare2(1, 1) 0], [cameraSquare2(1, 2) 0], [cameraSquare2(1, 3) 0], "Color", 'g');
line([cameraSquare3(1, 1) 0], [cameraSquare3(1, 2) 0], [cameraSquare3(1, 3) 0], "Color", 'b');

% Draw the camera's rectagle for each picture
plot3(cameraSquare1(:, 1), cameraSquare1(:, 2), cameraSquare1(:, 3), 'r');
plot3(cameraSquare2(:, 1), cameraSquare2(:, 2), cameraSquare2(:, 3), 'g');
plot3(cameraSquare3(:, 1), cameraSquare3(:, 2), cameraSquare3(:, 3), 'b');
legend('Cube', '', '' ,'' , '', '', '', '' ,'', '1st Image Camera', '2nd Image Camera', '3rd Image Camera');

hold off;


%% Helper Functions

% Used to compute the camera location given the P and the K
function [R, T, Camera_Loc] = getCameraLocation(P, K)

    % P = K[RT], K^-1 * P = RT
    RT = inv(K) * P;
    
    % Extract the R and T from each
    R = RT(1:3, 1:3);
    T = RT(1:3, 4);
    
    % Get the camera location
    Camera_Loc = (inv(R) * T).*-1;

    check = R * [1 0 0 -1*Camera_Loc(1); 0 1 0 -1*Camera_Loc(2); 0 0 1 -1*Camera_Loc(3)];

end

% Get the Camera Rectangke
function [points] = getCameraLens(cameraLocation, rotationMatrix, cameraSize)

    % convert pixels into mm
    cameraSize = cameraSize./4;
    cameraLocation = cameraLocation./4;

    % rotate the matrix to get the x, y, z values on rows
    rotationMatrix = rotationMatrix.';

    x = cameraLocation(1);
    y = cameraLocation(2);
    % Z needs to be swapped as the original points are heading towards the
    % negative, but the graph is heading towards positive
    z = cameraLocation(3).*-1;

    % Create the points for a square
    points = [
        x y z;
        x+cameraSize(2) y z;
        x+cameraSize(2) y z-cameraSize(1);
        x y z-cameraSize(1);
        x y z;
    ];

    % Apply the rotation to each point (upscaled to see the result)
    for i = 1 : 5

        points(i, :) = points(i, :) + 500.*(rotationMatrix * points(i, :).').';

    end


end

function [points] = getCameraLensTry(T, rotationMatrix, cameraSize)

    % convert pixels into mm
    cameraSize = cameraSize./4;

    % Create the points for a square
    points = [
        T(1) T(2) T(3);
        T(1) T(2)+1 T(3);
        T(1) T(2)+1 T(3)+1;
        T(1) T(2) T(3)+1;
        T(1) T(2) T(3);
    ];

    % Apply the rotation to each point (upscaled to see the result)
    for i = 1 : 5

        points(i, :) = -1.*((inv(rotationMatrix) * points(i, :).')).';

    end


end

% Get P
function [P, Verify] = getCameraProjectionModel(originXYZ_Points, cameraCoordinatePoints, realWorldPoints, showGraph, originalImage)

    if showGraph == true
        % Show original image
        imshow(originalImage);
        hold on
        % Shows the axes
        line([originXYZ_Points(1, 1) originXYZ_Points(2, 1)], [originXYZ_Points(1, 2) originXYZ_Points(2, 2)], 'Color', 'y', 'LineWidth', 4);
        line([originXYZ_Points(1, 1) originXYZ_Points(3, 1)], [originXYZ_Points(1, 2) originXYZ_Points(3, 2)], 'Color', 'c', 'LineWidth', 4);
        line([originXYZ_Points(1, 1) originXYZ_Points(4, 1)], [originXYZ_Points(1, 2) originXYZ_Points(4, 2)], 'Color', 'm', 'LineWidth', 4);
        
        % Shows the points selected
        for i = 1 : length(cameraCoordinatePoints)
            plot(cameraCoordinatePoints(i, 1), cameraCoordinatePoints(i, 2), Marker="o", MarkerSize=12, LineWidth=2);
        end
        
        legend(["X Axis", "Y Axis", "Z Axis", "Point 1", "Point 2", "Point 3", "Point 4", "Point 5", "Point 6"]);
        hold off
    end

    % Normalize Matrix
    % Calculate Average
    mean_pic = mean(cameraCoordinatePoints, 1);
    mean_rw = mean(realWorldPoints, 1);
    
    % Calculate Standard Deviation
    sigma_pic = 0;
    sigma_rw = 0;
    
    for i = 1 : length(cameraCoordinatePoints)
    
        sigma_pic = sigma_pic + (cameraCoordinatePoints(i, 1) - mean_pic(1))^2 + (cameraCoordinatePoints(i, 2) - mean_pic(2))^2;
    
        sigma_rw = sigma_rw + (realWorldPoints(i, 1) - mean_rw(1))^2 ...
                            + (realWorldPoints(i, 2) - mean_rw(2))^2 ...
                            + (realWorldPoints(i, 3) - mean_rw(3))^2;
    
    end
    
    sigma_pic = sigma_pic / (2 * length(cameraCoordinatePoints));
    sigma_pic = sqrt(sigma_pic);
    
    sigma_rw = sigma_rw / (3 * length(realWorldPoints));
    sigma_rw = sqrt(sigma_rw);
    
    % Apply normalization
    
    norm_points = cameraCoordinatePoints;
    norm_rw_points = realWorldPoints;
    
    for i = 1 : length(cameraCoordinatePoints)
    
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
    
    % Estimate camera projection matrix
    
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
    Verify = A * M;
    
    % Un-normalize P (Inv(M1) * P * M2)
    P = M1 \ P * M2;

end