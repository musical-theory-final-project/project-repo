const VF = Vex.Flow;

// Create an SVG renderer and attach it to the DIV element named "boo".
var vf = new VF.Factory({renderer: {selector: 'boo'}});
var score = vf.EasyScore();
var system = vf.System();

system.addStave({
//  voices: [score.voice(score.notes('A4/q, B4/h, C5/q'))]
//}).addClef('treble').addTimeSignature('4/4');
voices: [score.voice(score.notes('C5/w'))]
}).addClef('treble');

vf.draw();