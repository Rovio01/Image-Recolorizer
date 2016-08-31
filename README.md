# Image-Recolorizer
Note: DOES NOT WORK YET
Most programs that change one color for another in a program will just cull all colors that are close enough to the color being replaced, and replace those pixels with the color specified. However, this removes all shading and generally looks pretty terrible. The goal of this project is to instead alter the hue of each pixel by translating to HSV (hue, saturation, value) form and then simply altering the hue. While this approach only works on two-tone images, it ends up looking a lot nicer than other methods.
