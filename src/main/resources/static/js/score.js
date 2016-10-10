//const VF = Vex.Flow;
//
//// Create an SVG renderer and attach it to the DIV element named "boo".
//var vf = new VF.Factory({renderer: {selector: 'boo'}});
//var score = vf.EasyScore();
//var system = vf.System();
//
//system.addStave({
//  voices: [score.voice(score.notes('C#5/q, B4, A4, G#4'))]
//}).addClef('treble').addTimeSignature('4/4');
//
//vf.draw();



VF = Vex.Flow;

// Create an SVG renderer and attach it to the DIV element named "boo".
var div = document.getElementById("boo");
console.log("div:" + div);
var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
console.log("renderer: " + renderer);

// Configure the rendering context.
renderer.resize(500, 500);
var context = renderer.getContext();
context.setFont("Arial", 10, "").setBackgroundFillStyle("#eed");

// Create a stave of width 400 at position 10, 40 on the canvas.
var stave = new VF.Stave(10, 40, 400);

// Add a clef and time signature.
stave.addClef("treble").addTimeSignature("4/4");


console.log("score.js is working");