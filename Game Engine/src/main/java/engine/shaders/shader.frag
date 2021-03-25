#version 330 core

layout (location = 0) out vec4 out_Color;

in vec2 texCoord;
in vec3 normal;
in vec3 fragPos;

uniform vec3 camPos;
uniform sampler2D textureSampler;
uniform vec4 color;

void main(){
	out_Color = texture(textureSampler, texCoord) + color;

	out_Color.r += 0.5;
	out_Color.b *= 10;

	if(out_Color.a < 0.1)
		discard;

	float dist = length(camPos - fragPos); //Light attenuates with distance to the player; the player in essence holds a lamp
	out_Color = out_Color * clamp(100 / (dist * dist), 0, 1);
}
