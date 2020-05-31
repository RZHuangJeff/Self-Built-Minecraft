#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoord1;

uniform mat4 orthoMatrix;
uniform mat4 worldMatrix;

out vec2 outTexCoord;

void main(){
    gl_Position = orthoMatrix * worldMatrix * vec4(position, 1.0);
    
    outTexCoord = texCoord1;
}