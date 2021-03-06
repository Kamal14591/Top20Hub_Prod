// testing purpose

myApp.directive('fileModel', ['$parse', function ($parse) {
 return {
 restrict: 'A',
 link: function(scope, element, attrs) {
 var model = $parse(attrs.fileModel);
 var modelSetter = model.assign;

element.bind('change', function(){
 scope.$apply(function(){
 modelSetter(scope, element[0].files[0]);
 });
 });
 }
 };
 }]);

myApp.service('fileUpload', ['$q','$http', function ($q,$http) {
 var deffered = $q.defer();
 var responseData;
 this.uploadFileToUrl = function(file, uploadUrl){
 var fd = new FormData();
 fd.append('file', file);
 return $http.post(uploadUrl, fd, {
 transformRequest: angular.identity,
 headers: { 'Content-Type' : undefined}
 })
 .success(function(response){

/* $scope.errors = response.data.value; */
 console.log(response);
 responseData = response;
 deffered.resolve(response);
 return deffered.promise;
 })
 .error(function(error){
 deffered.reject(error);
 return deffered.promise;
 });

}

this.getResponse = function() {
 return responseData;
 }

}]);

myApp.controller('myCtrl', ['$scope', '$q', 'fileUpload', function($scope, $q, fileUpload){
 $scope.dataUpload = true;
 $scope.errVisibility = false;
 $scope.uploadFile = function(){
 var file = $scope.myFile;
 console.log('file is ' );
 console.dir(file);

var uploadUrl = "<give-your-url>/continueFileUpload";
 fileUpload.uploadFileToUrl(file, uploadUrl).then(function(result){
 $scope.errors = fileUpload.getResponse();
 console.log($scope.errors);
 $scope.errVisibility = true;
 }, function(error) {
 alert('error');
 })

};
 }]);
 