folder_name = 'Backyard';
start_frame = 7;
stop_frame = 13;
outfile = "Backyard_Results\backyard.mp4";

vidObj = VideoWriter(outfile, 'MPEG-4');

open(vidObj);

for i=start_frame:stop_frame
    demo_optical_flow(folder_name,i,i+1); %i+1 for subsequent frames
    frame = getframe(gcf);
    saveas(gcf, "Backyard_Results\frame" + i + ".png");
    writeVideo(vidObj,frame);
    fprintf('Frame #: %d\n',i)
end

close(vidObj);
