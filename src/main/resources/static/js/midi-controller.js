    angular.module('MidiApp', [])
   .controller('midi-controller', function($scope, $http) {
    $scope.user;
    $scope.intervalLevel;
    $scope.initialInterval;
    $scope.intervalScoring = [];
    $scope.isLive = false;
    $scope.filter;

    const VF = Vex.Flow;
    $scope.frequencies = {};
    $scope.staff;
    $scope.note;
    $scope.nextNote;

    var teoriaContainer;
    $scope.teoriaOutput;
    var a4;

        $scope.login = function(loginContainer) {
            console.log(loginContainer);
            $http.post("/login.json", loginContainer)
            .then (
                function successCallBack(response) {
                    console.log(response.data);
                    console.log("logging in...");
                    var userStatus = response.data;
                    $scope.user = userStatus.user;
                    $scope.intervalLevel = userStatus.intervalLevel;
                    console.log($scope.user);
                    $scope.isLive = true;
                },
                function errorCallBack(response) {
                    console.log("Unable to log in");
                });
             console.log("done with callback");
        };

        $scope.register = function(registerContainer) {
            console.log(registerContainer);
            $http.post("/register.json", registerContainer)
            .then(
                function successCallBack(response) {
                    console.log(response.data);
                    console.log("Registering...");
                    $scope.user = response.data;
                    $scope.isLive = true;
                },
                function errorCallBack(response) {
                    console.log("Unable to register");
                });
             console.log("done with callback");
        };

        $scope.getUser = function () {
            console.log("getting user from session");
            $http.post("/getUserFromSession.json")
            .then(
                function successCallBack(response) {
                    console.log(response.data);
                    var everything = response.data;
                    $scope.user = everything.user;
                    $scope.intervalLevel = everything.intervalLevel;
                },
                function errorCallBack(response) {
                    console.log("unable to get user");
                });
        };

        $scope.getIntervalLevel = function() {
            console.log("getting Intervals level");
            console.log($scope.user);
            $http.post("/getIntervalLevel.json", $scope.user)
            .then(
                function successCallBack(response) {
                    console.log(response.data);
                    $scope.intervalLevel = response.data;
                    console.log($scope.intervalLevel);
                },
                function errorCallBack(response) {
                    console.log("Could not return level");
                });
//                $scope.getInitialInterval();
        };

        $scope.setCurrentIntervalLevel = function(intLevel) {
            console.log("setting interval level for user");
            $scope.user.currentIntervalLevel = intLevel;
            console.log($scope.user);
            $http.post("/getDesiredLevel.json", $scope.user)
            .then(
                function successCallBack(response) {
                    console.log("User updated");
                    $scope.intervalLevel = response.data;
                    console.log($scope.intervalLevel);
                },
                function errorCallBack(response) {
                    console.log("unable to update user");
                });
        };

        $scope.getInitialInterval = function() {
            console.log("Getting initial interval");

            $http.post("/getInterval.json", $scope.user.currentIntervalLevel)
            .then (
                function successCallBack(response) {
                    console.log(response.data);
                    $scope.initialInterval = response.data;
                    console.log($scope.initialInterval);
                },
                function errorCallBack(response) {
                    console.log(response);
                    console.log("Unable to recieve initial interval");
                });
        };

        $scope.goToIntervalGames = function() {
            console.log("Going to the interval games menu");
            $http.get("/intervalGameMenu")
            .then(
                function successCallBack(response) {
                    console.log("Great success!");
                },
                function errorCallBack(response){
                    console.log("Could not navigate to page");
                });
        };


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

        	    //trying scales

        	    var scale = new MajorScale();
        	    var baseFrequency = 261.63;
        	    var octave = 0;
        	    var freq1 = scale.getFrequency(0, baseFrequency, octave);
        	    var freq2 = scale.getFrequency(1, baseFrequency, octave);
        	    var freq3 = scale.getFrequency(2, baseFrequency, octave);
        	    var freq4 = scale.getFrequency(3, baseFrequency, octave);
        	    var freq5 = scale.getFrequency(4, baseFrequency, octave);

//        	    var melodyA = new PSequence([262, 294, 330, 349]);
//        	    var melodyB = new PSequence([349, 330, 349, 392]);
//        	    var melodyC = new PSequence([440, 392, 349, 330]);
        	    var note = new PSequence([440]);

                var frequencyPattern = new PSequence([freq1, freq2, freq3, freq4, freq5], 1);
                var durationPattern = new PChoose([new PSequence([2])], Infinity);
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


        $scope.userInput = function() {
            $http.post("/getIntervalLevel.json", $scope.intervalLevel)
            .then(function successCallBack(res){
              console.log(res);
              $scope.intervalLevel = res.data;
              $http.post("getInterval.json", $scope.intervalLevel).then(function successCallBack(res){
              $scope.initialInterval = res.data;
               var vf = new VF.Factory({renderer: {selector: 'boo'}});
               var score = vf.EasyScore();
               var system = vf.System();
               console.log($scope.initialInterval);
               system.addStave({
                   voices:[score.voice(score.notes($scope.initialInterval.note + $scope.initialInterval.octave + '/w'))]
               }).addClef('treble').addTimeSignature('4/4');

               vf.draw();
              }, function errorCallBack(err){
                console.log(err);
              })

            },function errorCallBack(err){
              console.log(err);
            });
        }


        $scope.chords = function() {
            var Synth = function(audiolet, frequency) {
                AudioletGroup.call(this, audiolet, 0, 1);
                // Basic wave
                this.saw = new Saw(audiolet, frequency);
                // Gain envelope
                this.gain = new Gain(audiolet);
                this.env = new PercussiveEnvelope(audiolet, 1, 0.1, 0.1,
                    function() {
                        this.audiolet.scheduler.addRelative(0, this.remove.bind(this));
                    }.bind(this)
                );
                this.envMulAdd = new MulAdd(audiolet, 0.3, 0);
                // Main signal path
                this.saw.connect(this.gain);
                this.gain.connect(this.outputs[0]);
                // Envelope
                this.env.connect(this.envMulAdd);
                this.envMulAdd.connect(this.gain, 0, 1);
            };
            extend(Synth, AudioletGroup);

            var SchedulerApp = function() {
                this.audiolet = new Audiolet();

                this.scale = new MajorScale();

                // I IV V progression
                var chordPattern = new PSequence([[0, 2, 4],
                                                  [3, 5, 7],
                                                  [4, 6, 8]]);
                // Play the progression
                this.audiolet.scheduler.play([chordPattern], 1,
                                             this.playChord.bind(this));
            };

            SchedulerApp.prototype.playChord = function(chord) {
                for (var i = 0; i < chord.length; i++) {
                    var degree = chord[i];
                    var frequency = this.scale.getFrequency(degree, 16.352, 4);
                    var synth = new Synth(this.audiolet, frequency);
                    synth.connect(this.audiolet.output);
                }
            };
            var app = new SchedulerApp();
        };

        $scope.playInterval = function() {
            console.log($scope.initialInterval.note + $scope.initialInterval.octave);
            var noteOut = teoria.note($scope.initialInterval.note + $scope.initialInterval.octave);
            var nextNote = noteOut.interval($scope.initialInterval.interval);
            $scope.nextNote = nextNote.toString();
            console.log(noteOut);
            console.log(nextNote.toString());
            var freq1 = noteOut.fq();
            var freq2 = nextNote.fq();
            console.log("Note 1 freq=" + freq1 + "; Note 2 freq=" + freq2);
            $scope.a4 = noteOut.fq();
            $scope.frequencies = {
                freq1: freq1,
                freq2: freq2
             };
             console.log($scope.frequencies);


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
                //trying scales
                var interval = new PSequence([$scope.frequencies.freq1, $scope.frequencies.freq2]);

                var durationPattern = new PChoose([new PSequence([2])], Infinity);
                var frequencyPattern = new PSequence([interval], 2);

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

        $scope.playScale = function(scale) {
            var scale = scale;
            var initialNote = teoria.note(scale.startNote + scale.octave);
            console.log(initialNote.toString());
            var myScale = initialNote.scale(scale.name);
            console.log(myScale);
            var scaleFreq = [];

            for (var count = 0; count < myScale.scale.length; count++) {
                var newNote = teoria.interval(initialNote, myScale.scale[count]);
                newNote = newNote.fq();
                console.log(newNote);
                scaleFreq.push(newNote);
            };
            console.log(scaleFreq);

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

            var AudioletApp = function(scale) {
                this.audiolet = new Audiolet();
                var audioScale;
                if (scale.name === "major") {
                    audioScale= new MajorScale();
                } else if (scale.name === "minor") {
                    audioScale = new MinorScale();
                }
                var baseFrequency = scaleFreq[0];
                var octave = 0;
                var freq1 = audioScale.getFrequency(0, baseFrequency, octave);
                var freq2 = audioScale.getFrequency(1, baseFrequency, octave);
                var freq3 = audioScale.getFrequency(2, baseFrequency, octave);
                var freq4 = audioScale.getFrequency(3, baseFrequency, octave);
                var freq5 = audioScale.getFrequency(4, baseFrequency, octave);
                var freq6 = audioScale.getFrequency(5, baseFrequency, octave);
                var freq7 = audioScale.getFrequency(6, baseFrequency, octave);

                var note = new PSequence([440]);

                var frequencyPattern = new PSequence([freq1, freq2, freq3, freq4, freq5, freq6, freq7], 1);
                var durationPattern = new PChoose([new PSequence([1])], Infinity);

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

        $scope.checkAnswer = function(noteInterval) {
        console.log(noteInterval);
        console.log($scope.initialInterval.interval);
            if ($scope.initialInterval.interval === noteInterval) {
                console.log("You are the greetest!");
                if ($scope.intervalScoring.length < 10) {
                    $scope.intervalScoring.push(true);
                } else {
                    $scope.intervalScoring.shift();
                    $scope.intervalScoring.push(true);
                }
            } else {
                console.log("Blargh");
                if ($scope.intervalScoring.length < 10) {
                    $scope.intervalScoring.push(false);
                } else {
                    $scope.intervalScoring.shift();
                    $scope.intervalScoring.push(false);
                }
            }
            function isTrue(value) {
                return value === true;
            };
            console.log($scope.intervalScoring);
            $scope.filter = $scope.intervalScoring.filter(isTrue);
            console.log($scope.filter);

        };

        $scope.checkNoteName = function(noteNotation) {
            console.log(noteNotation);
            console.log($scope.nextNote);
            if ($scope.nextNote === noteNotation) {
                console.log("A winrar is you!");
            } else {
                console.log("I'm sorry, but you're wrong.");
            }
        };

        $scope.nextIntervalLevel = function() {
            console.log("Moving to level " + ($scope.intervalLevel.levelNumber + 1) + " from " + $scope.intervalLevel);
            $http.post("/nextIntervalLevel.json", $scope.user)
            .then(
                function successCallBack(response) {
                    console.log("We're moving on~~~");
                    $scope.intervalLevel = response.data;
                    console.log($scope.intervalLevel);
                    $scope.filter = [];
                    $scope.intervalScoring = [];
                },
                function errorCallBack(response) {
                    console.log("Could not move to next level");
                });
        };

        $scope.getIntervalLevel();

   });
