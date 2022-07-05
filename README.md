# Spirograph

This is an exercise is function composition and code reading.

## Tasks

1. Read the code. What structure do you see in the code. For example:

- what uses of composition are there in the code?
- what strategy does `makeImage` use?

2. Run the code. Does it work? What output do you see? Is it the output you expect?

3. Implement a method to construct a [hypocycloid](https://en.wikipedia.org/wiki/Hypocycloid) curve. 

A hypocycloid is defined by two parameters: an inner and an outer radius. A hypocycloid is the addition of two circles. The first circle has radius (outer radius - inner radius). The second circle has the radius of the inner circle and rotates with a speed equal to (inner radius - outer radius) / inner radius.

Your implement should be a composition of existing methods and the maths required to implement the equations above.

4. Implement a method to construct a [hypotrochoid](https://en.wikipedia.org/wiki/Hypotrochoid) curve.

A hypotrochoid is a hypocycloid with an additional parameter: the offset from the center of the smaller circle. It's implementation is the same as the hypocycloid except the second has the radius of the offset.

5. Create a grid showing hypotrochoids where the ratio of inner to outer radius is in 1:3, 1:4, 1:5, 2:5, 2:5, and 2:7, and the offset is in 0.1, 0.3, 0.5, 0.7, 0.9, 1.1, 1.3, and 1.5.
