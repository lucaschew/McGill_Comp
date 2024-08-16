%% Setup Image

Iorig = imread('wheatcypress.png');
Iorig_d = double(Iorig);

% Convert channels to double
Ir = im2double(Iorig(:,:,1));
Ig = im2double(Iorig(:,:,2));
Ib = im2double(Iorig(:,:,3));

%% 1a) Linear Diffusion with Heat Equation

stddev = [2 4 8 12 16];
stdSize = 5;
deltaT = 0.125;

for i = 1 : stdSize

    std = stddev(i);

    % Gaussian Filter
    imgR = imgaussfilt(Ir, std);
    imgG = imgaussfilt(Ig, std);
    imgB = imgaussfilt(Ib, std);

    heatR = Ir;
    heatG = Ig;
    heatB = Ib;

    jump = 1 / (2 * (std)^2);

    % From lecture, t = 1/2σ^(2) is equivalent to σ in Gaussian Blur
    % Forming Heat Equation's Blur
    for t = 0 : jump : 2

        % Create First Derivative for Heat Equation
        [xgradR, ygradR] = gradient(heatR);
        [xgradG, ygradG] = gradient(heatG);
        [xgradB, ygradB] = gradient(heatB);
        
        % Create Second Derivative for Heat Equation
        xxgradR = gradient(xgradR);
        [yxgradR, yygradR] = gradient(ygradR);
        
        xxgradB = gradient(xgradB);
        [yxgradB, yygradB] = gradient(ygradB);
        
        xxgradG = gradient(xgradG);
        [yxgradG, yygradG] = gradient(ygradG);


        heatR = heatR + (deltaT * (xxgradR + yygradR));
        heatG = heatG + (deltaT * (xxgradG + yygradG));
        heatB = heatB + (deltaT * (xxgradB + yygradB));

    end

    % Combining the results to create a diffused colour image and display it
    Idiffused = cat(3, imgR, imgG, imgB);
    Iheat = cat(3, heatR, heatG, heatB);
    imshow(Idiffused);
    title("Gaussian at " + std + "σ");
    figure();
    imshow(Iheat);
    title("Heat Equivalent to " + std + "σ");
    figure();
    plotDiff = Idiffused - Iheat;
    imshow(plotDiff);
    title("Difference between plots on " + std + "σ");
    figure();

end

%{

In a majority of the difference graphs, the only part where there are
differences, is the edges of the picture. I believe this is because we are
taking the approximate derivative of each X and Y value, and since my
code repeats the heat equation around 1024 times for 16σ, they add up and
eventually cause the main picture to stand-up away from the corners, which
are not changing as quickly.

%}

%% 1b) Nonlinear Diffusion: Perona-Malik Equation

stddev = [2 4 8 12 16];
stdSize = 5;
k = 0.01;
deltaT = 0.125;

pmR = Ir;
pmG = Ig;
pmB = Ib;

for i = 1 : stdSize

    std = stddev(i);

    pmR = Ir;
    pmG = Ig;
    pmB = Ib;

    jump = 1 / (2 * (std)^2);

    for t = 0 : jump : 2

        % Gradient of I (∇I)
        [xgradR, ygradR] = gradient(pmR);
        [xgradG, ygradG] = gradient(pmG);
        [xgradB, ygradB] = gradient(pmB);

        % C 
        magR = sqrt(xgradR.^2 + ygradR.^2);
        magG = sqrt(xgradG.^2 + ygradG.^2);
        magB = sqrt(xgradB.^2 + ygradB.^2);
        
        cR = 1./ (1 + ( magR.^2 / k ));
        cG = 1./ (1 + ( magG.^2 / k ));
        cB = 1./ (1 + ( magB.^2 / k ));


        % Apply the Divergence
        dR = divergence(cR.* xgradR, cR.* ygradR);
        dG = divergence(cB.* xgradG, cB.* ygradG);
        dB = divergence(cG.* xgradB, cG.* ygradB);

        % Increment based on delta T
        pmR = pmR + (deltaT * dR);
        pmG = pmG + (deltaT * dG);
        pmB = pmB + (deltaT * dB);
        

    end

    Ipm = cat(3, pmR, pmG, pmB);
    imshow(Ipm);
    title("Perona-Malik Equation Equivalent of " + std + "σ");
    figure();

end

%{

Increasing k (kappa) would decrease the blur around all the other parts of
the image, as the C for each pixel would be much higher than if it was set
to a lower value.

The edges of the image is more apparent when using this equation compared
to the heat equation, as the C preserves the edges, even within the image
and not just the border.


%}


%% 1c) Nonlinear Diffusion: Geometric Heat Equation

stddev = [2 4 8 12 16];
stdSize = 5;
deltaT = 0.125;

for i = 1 : stdSize

    std = stddev(i);

    ghR = Ir;
    ghG = Ig;
    ghB = Ib;

    jump = 1 / (2 * (std)^2);

    for t = 0 : jump : 2

        % Gradient of I (∇I)
        [xgradR, ygradR] = gradient(ghR);
        [xgradG, ygradG] = gradient(ghG);
        [xgradB, ygradB] = gradient(ghB);

        % Create magnitude of I
        magR = sqrt(xgradR.^2 + ygradR.^2);
        magG = sqrt(xgradG.^2 + ygradG.^2);
        magB = sqrt(xgradB.^2 + ygradB.^2);

        magR = magR + eps;
        magG = magG + eps;
        magB = magB + eps;

        dR = divergence(xgradR./magR, ygradR./magR);
        dG = divergence(xgradG./magG, ygradG./magG);
        dB = divergence(xgradB./magB, ygradB./magB);

        ghR = ghR + (deltaT * magR.* dR);
        ghG = ghG + (deltaT * magG.* dG);
        ghB = ghB + (deltaT * magB.* dB);
        
    end

    Ipm = cat(3, ghR, ghG, ghB);
    imshow(Ipm);
    title("Geometric Heat Equation Equivalent of " + std + "σ");
    figure();

end