#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textCoord;
layout (location = 5) in mat4 worldMatrix;
layout (location = 9) in vec2 textOffset;

out vec2 outTextCoord;

uniform mat4 projectionMatrix;

uniform int numOfCol;
uniform int numOfRow;

void main(){
    gl_Position = projectionMatrix * worldMatrix * vec4(position, 1.0);
    
    outTextCoord = vec2(textCoord.x/numOfCol + textOffset.x, textCoord.y/numOfRow + textOffset.y);
}