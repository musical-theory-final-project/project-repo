const VF = Vex.Flow;

// Create an SVG renderer and attach it to the DIV element named "boo".
var vf = new VF.Factory({renderer: {selector: 'boo'}});
var score = vf.EasyScore();
var system = vf.System();

system.addStave({
  voices: [score.voice(score.notes('C#5/q, B4, A4, G#4'))]
}).addClef('treble').addTimeSignature('4/4')
//voices: [score.voice(score.notes('B4'))]
//}).addClef('treble');

vf.draw();
