# Project Description
The Hologon Project is a acrylic hologram projector with a Java application (that's all the code here) that allows users to load videos and 3D models into a projector-ready display. A link to our Devpost for the project can be found [here](https://devpost.com/software/wonderhack-2020).

# Inspiration
We had seen "hologram projects" floating around the internet, but we found that converting your own videos to a format that could be displayed with this homemade projector was difficult given the resources we could find. This inspired us to build our own, larger hologram projector and create an application that would allow the conversion of video and 3D models for display.

# How We Built
For the application, we used JavaFX to set up, manipulate, and display the media. We manipulate the movement of the 3D models within the Java program, meaning all we need is a raw .stl file containing the model. As for the physical projector, we use a large monitor and 4 sheets of acrylic to display our converted video. The acrylic is cut at a specific angle to allow for maximum viewing space.

# What We Learned
 - How to take the angle between 2 3D vectors
 - How to synchronize the axes of separate 3D models
 - How to resize and manipulate video using Java
 - How to manipulate 3D models using Java
And most importantly...
 - How to stay up way too late

# Challenges We Faced
 - Figuring out a way to synchronize the axes of separate 3D models
 - Creating a intuitive, easy-to-use User Interface that held all the required settings
 - Finding a way to render only what needed to be rendered on a 3D object
