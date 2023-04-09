#version 460

const float PI = 3.1415926;
const float E = 2.7182818;
const float EPSILON = 0.01;
const float G = 6.6743 * pow(10.0, -11);
const float LIGHT_SPEED = 299792458.0;
const float AU = 149597870700.0;
const int BODY_NUM_LIMIT = 50;
const int LIGHT_SOURCES_NUM = 1;
const bool IS_BUMP = false;

uniform vec2 uResolution;
uniform vec3 uCameraPos;
uniform float uMaxDist;
uniform float uMaxSteps;
uniform float uFov;
uniform vec2 uMousePos;
uniform float uTime;
uniform vec3 uPos;
uniform mat4 uView;
uniform vec3 uOffset;
uniform int uSelectedBodyIndex;

uniform int uBodyNum;
uniform int uLightSourcesNum;
uniform int uVisibilities[BODY_NUM_LIMIT];
uniform float uIDs[BODY_NUM_LIMIT];
uniform vec3 uPositions[BODY_NUM_LIMIT];
uniform vec3 uDimensions[BODY_NUM_LIMIT];
uniform float uMasses[BODY_NUM_LIMIT];
uniform float uRotationSpeeds[BODY_NUM_LIMIT];
uniform float uAxisInclinations[BODY_NUM_LIMIT];
uniform float uApparentMagnitudes[BODY_NUM_LIMIT];
uniform vec3 uColors[BODY_NUM_LIMIT];

uniform sampler2D uTextures[BODY_NUM_LIMIT];
uniform float uBump[BODY_NUM_LIMIT];
uniform sampler2D uBumpTextures[BODY_NUM_LIMIT];

uniform int uRings[BODY_NUM_LIMIT];
uniform vec2 uRingRadiuses[BODY_NUM_LIMIT];
uniform sampler2D uRingTextures[BODY_NUM_LIMIT];

uniform vec3 uDarkMatterPositions[BODY_NUM_LIMIT];
uniform float uDarkMatterDensities[BODY_NUM_LIMIT];
uniform vec3 uDarkMatterColors[BODY_NUM_LIMIT];

uniform sampler2D uBackgroundTexture;

uniform int uEnableDarkMatter;
uniform int uEnableLighting;
uniform int uShowGrid;