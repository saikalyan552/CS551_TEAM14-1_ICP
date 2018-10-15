angular.module('numbersApp', ['ngSanitize']).controller('NamesCtrl', function ($scope, $http) {
    $scope.getNumbersInfo = function () {
        var month = $("#month").val();
        var date = $("#date").val();
        // Hiding all errors and outputs first
        $("#fielderror").hide();
        $("#output").hide();

        // Validations
        if (!(month && date)) {
            // Throw error to say they should not be empty
            $scope.fielderrorhtml = "Year or Month cannot be empty";
            $("#fielderror").show();
            return false;
        } else if (isNaN(month) || isNaN(date)) {
            // Throw error to say they should be only numbers
            $scope.fielderrorhtml = "Year or Month should be in Number format (Ex: 01)";
            $("#fielderror").show();
            return false;
        }

        var numbersOutput = $http.get("http://numbersapi.com/" + month + "/" + date + "/date?json");
        numbersOutput.success(function (data) {

            if (data == null || data.text == null) {
                // Throw error to say no records found
                $scope.fielderrorhtml = "No data found for the year" + year + " & month " + month + " passed";
                $("#fielderror").show();
                return false;
            }
            $scope.output = data.text;
            $("#output").show();
        })
        numbersOutput.error(function (data) {
            alert("There was some error processing your request. Please try after some time.");
        });
    }
});
