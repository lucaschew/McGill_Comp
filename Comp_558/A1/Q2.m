% Create Original Array

L = size(200);

for i = 1 : 200
    if (i >= 40 && i <= 60)
        L(i) = 125;
    elseif (i >= 140 && i <= 160)
        L(i) = 75;
    else
        L(i) = 100;
    end
end

%% 
% Q2a) Create Gaussian Normalizaed 

mean = 0; 
% I found that changing the range to be larger made it easier to see the
% curves
range = 2;

% 2
stddev2 = 2; 
x2 = linspace((-1 * range) * stddev2, range * stddev2, 101);
y2 = 1/(2*pi*stddev2)*exp(-(x2-mean).^2/(2*stddev2^2));
y2 = y2 / sum(y2);

% 4
stddev4 = 4;
x4 = linspace((-1 * range) * stddev4, range * stddev4, 101);
y4 = 1/(2*pi*stddev4)*exp(-(x4-mean).^2/(2*stddev4^2));
y4 = y4 / sum(y4);

% 8
stddev8 = 8;
x8 = linspace((-1 * range) * stddev8, range * stddev8, 101);
y8 = 1/(2*pi*stddev8)*exp(-(x8-mean).^2/(2*stddev8^2));
y8 = y8 / sum(y8);

hold on;

plot(x2, y2, "DisplayName", "2σ");
plot(x4, y4, "DisplayName", "4σ");
plot(x8, y8, "DisplayName", "8σ");
title("1D Discrete Figure");
xlabel("x-axis");
ylabel("y-axis");

lgd = legend;
lgd.Title.String = "Standard Deviation σ";

hold off;

%% 
% Q2b) Create First Derivative of Gaussian

dy = [1; 0; -1];
d1y2 = conv(y2, dy, 'same');
d1y4 = conv(y4, dy, 'same');
d1y8 = conv(y8, dy, 'same');

% Normalized
d1y2 = d1y2.*stddev2;
d1y4 = d1y4.*stddev2;
d1y8 = d1y8.*stddev2;

% 2
plot(x2, y2);
hold on;
plot(x2, d1y2);
title("1st Gaussian Derivative Figure 2σ")
xlabel("x-axis");
ylabel("y-axis");

legend('Original 2σ', '1st Derivative');
hold off;

% 4
plot(x4, y4);
hold on;
plot(x4, d1y4);
title("1st Gaussian Derivative Figure 4σ");
xlabel("x-axis");
ylabel("y-axis");

legend('Original 4σ', '1st Derivative');
hold off;

% 8
plot(x8, y8);
hold on;
plot(x8, d1y8);
title("1st Gaussian Derivative Figure 8σ");
xlabel("x-axis");
ylabel("y-axis");

legend('Original 8σ', '1st Derivative');
hold off;


%% 
% Q2c) Create Second Derivative of Gaussian

d2y2 = conv(d1y2, dy, 'same');
d2y4 = conv(d1y4, dy, 'same');
d2y8 = conv(d1y8, dy, 'same');

% Normalized
d2y2 = d2y2.*stddev2;
d2y4 = d2y4.*stddev2;
d2y8 = d2y8.*stddev2;

% 2
plot(x2, y2);
hold on;
plot(x2, d1y2);
plot(x2, d2y2);
title("1st Gaussian Derivative Figure 2σ");
xlabel("x-axis");
ylabel("y-axis");

legend('Original 2σ', '1st Derivative, 2nd Derivative');
hold off;

% 4
plot(x4, y4);
hold on;
plot(x4, d1y4);
plot(x4, d2y4);
title("2nd Gaussian Derivative Figure 4σ");
xlabel("x-axis");
ylabel("y-axis");

legend('Original 4σ', '1st Derivative', '2nd Derivative');
hold off;

% 8
plot(x8, y8);
hold on;
plot(x8, d1y8);
plot(x8, d2y8);
title("2nd Gaussian Derivative Figure 8σ");
xlabel("x-axis");
ylabel("y-axis");

legend('Original 8σ', '1st Derivative', '2nd Derivative');
hold off;


%%
% Q2d) Convulving with Original Signal

% Default Gaussian
og2 = conv(L, y2, 'same');
og4 = conv(L, y4, 'same');
og8 = conv(L, y8, 'same');

plot(L);
hold on;
plot(og2);
plot(og4);
plot(og8);

hold off;

% First Derivative
d1og2 = conv(L, d1y2, 'same');
d1og4 = conv(L, d1y4, 'same');
d1og8 = conv(L, d1y8, 'same');

plot(L);
hold on;
plot(d1og2);
plot(d1og4);
plot(d1og8);

hold off;

% Second Derivative
d2og2 = conv(L, d2y2, 'same');
d2og4 = conv(L, d2y4, 'same');
d2og8 = conv(L, d2y8, 'same');

plot(L);
hold on;
plot(d2og2);
plot(d2og4);
plot(d2og8);

hold off;

%{
The first derivative of the graph reveals the local maximas and minimas
of where the values change from a high to a low value or vice versa. This
is because the first derivative shows the velocity of the values, which
reveals the edges when they switch directions.
For the second derivative is reveals the point in which the gaussian
function is at its peak, as it is represented in the 2nd derivative graph
when the value is at the lowest. This can be useful as the peak of the
gaussian is location of the edge.

The locations of my detected edges are harder to see and make out in the
graph as the σ (standard deviation) gets larger, as the values are more
spread apart, making the lines and the gaussian smoother, which in term
make both the first and the second derivative smoother. As the standard
deviation approaches a large number, the line to determine the edges, both
in the first and second derivative, become a straight line, where it is
almost impossible to find the edge.
%}

