angular.module('MidiApp', [])
    .controller('testjs', function($scope, $http){
       console.log("Testing...");
       var answerContainer = ["t","f","t","f","t","f"];

       $scope.makeArray = function(arraySize) {
           var returnArray = [];
           for (var i = 0; i < arraySize; i++) {
               returnArray.push(i);
           }
           return returnArray;
       };
    });