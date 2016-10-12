angular.module('MidiApp', [])
   .controller('midi-controller', function($scope, $http) {
        var noteContainer;
        const VF = Vex.Flow;

        $scope.playExample = function() {
            var Synth = function(audiolet, frequency) {
            	AudioletGroup.apply(this, [audiolet, 0, 1]);

                this.audiolet = new Audiolet();
                this.sine = new Sine(this.audiolet, frequency);
                this.modulator = new Saw(this.audiolet, 2 * frequency);
                this.modulatorMulAdd = new MulAdd(this.audiolet, frequency/2, frequency);

                this.gain = new Gain(this.audiolet);
                this.envelope = new PercussiveEnvelope(this.audiolet, 1, 0.2, 0.5,
                	function() {
                		this.audiolet.scheduler.addRelative(0, this.remove.bind(this));
                	}.bind(this)
                	);

                this.modulator.connect(this.modulatorMulAdd);
                this.modulatorMulAdd.connect(this.sine);
                this.envelope.connect(this.gain, 0, 1);
                this.sine.connect(this.gain);
                this.gain.connect(this.outputs[0]);

            };

            var AudioletApp = function() {
        	    this.audiolet = new Audiolet();

//        	    var melodyA = new PSequence([262, 294, 330, 349]);
//        	    var melodyB = new PSequence([349, 330, 349, 392]);
//        	    var melodyC = new PSequence([440, 392, 349, 330]);
        	    var note = new PSequence([440]);

                var frequencyPattern = new PSequence([note], 1);
                var durationPattern = new PChoose([new PSequence([4])], Infinity);
//        	    var frequencyPattern = new PSequence([melodyA, melodyB, melodyC], 2);
//        	    var durationPattern = new PChoose([new PSequence([3, 1, 2, 2]),
//        	    								  new PSequence([2, 2, 1, 3]),
//        	    								  new PSequence([1, 2, 1, 2])],
//        	    								  Infinity);

        	    this.audiolet.scheduler.play([frequencyPattern], durationPattern,
        	    	function(frequency) {
        	    		var synth = new Synth(this.audiolet, frequency);
        	    		synth.connect(this.audiolet.output);
        	    	}.bind(this)
        	    	);
        	};

            extend (Synth, AudioletGroup);


            this.audioletApp = new AudioletApp();
        };

        $scope.score = function() {

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



        }
        $scope.score();
        $scope.userInput = function(noteContainer) {
            var vf = new VF.Factory({renderer: {selector: 'input'}});
            var score = vf.EasyScore();
            var system = vf.System();
            system.addStave({
                voices:[score.voice(score.notes(noteContainer))]
            }).addClef('treble').addTimeSignature('4/4');
            vf.draw();
        }



   });
