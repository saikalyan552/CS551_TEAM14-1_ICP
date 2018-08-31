angular.module('weather', ['ngSanitize'])
    .controller('weatherbtn', function($scope, $http) {

        $scope.getWeather = function () {
            $scope.cityName = document.getElementById("CityId").value;
            $scope.stateCode = document.getElementById("CodeId").value;

            $http.get('https://api.wunderground.com/api/36b799dc821d5836/hourly/q/' + $scope.stateCode + '/' + $scope.cityName + '.json').success(function (data) {
                console.log(data);
                temp0 = data.hourly_forecast[0].temp.english;
                icon0 = data.hourly_forecast[0].icon_url;
                weather0 = data.hourly_forecast[0].condition;
                temp1 = data.hourly_forecast[1].temp.english;
                icon1 = data.hourly_forecast[1].icon_url;
                weather1 = data.hourly_forecast[1].condition;
                temp2 = data.hourly_forecast[2].temp.english;
                icon2 = data.hourly_forecast[2].icon_url;
                weather2 = data.hourly_forecast[2].condition;
                temp3 = data.hourly_forecast[3].temp.english;
                icon3 = data.hourly_forecast[3].icon_url;
                weather3 = data.hourly_forecast[3].condition;
                temp4 = data.hourly_forecast[4].temp.english;
                icon4 = data.hourly_forecast[4].icon_url;
                weather4 = data.hourly_forecast[4].condition;
                temp5 = data.hourly_forecast[5].temp.english;
                icon5 = data.hourly_forecast[5].icon_url;
                weather5 = data.hourly_forecast[5].condition;

                $scope.currentweather = {
                    html: "Currently " + temp0 + "  ° F and " + weather0 + " " + "<br/><img src='" + icon0 + "'/><br/>" +
                        "In one hour " + temp1 + "  ° F and " + weather1 + " " + "<br/><img src='" + icon1 + "'/><br/>" +
                        "In two hours " + temp2 + "  ° F and " + weather2 + " " + "<br/><img src='" + icon2 + "'/><br/>" +
                        "In three hours " + temp3 + "  ° F and " + weather3 + " " + "<br/><img src='" + icon3 + "'/><br/>" +
                        "In four hours " + temp4 + "  ° F and " + weather4 + " " + "<br/><img src='" + icon4 + "'/><br/>" +
                        "In five hours " + temp5 + "  ° F and " + weather5 + " " + "<br/><img src='" + icon5 + "'/><br/>"
                }
            })
            $scope.abbreviation = input;

        }

    });