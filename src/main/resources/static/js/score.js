const VF = Vex.Flow;

// Create an SVG renderer and attach it to the DIV element named "boo".
var vf = new VF.Factory({renderer: {selector: 'boo'}});
var vf2 = new VF.Factory({renderer: {selector: 'boo2'}});
var score = vf.EasyScore();
var score2 = vf2.EasyScore();
var system = vf.System();
var system2 = vf2.System();

system.addStave({
  voices: [score.voice(score.notes('C#5/q, B4, A4, G#4'))]
}).addClef('treble').addTimeSignature('4/4');

vf.draw();



system2.addStave({
  voices: [score2.voice(score2.notes('A4/w'))]
}).addClef('treble');

vf2.draw();
