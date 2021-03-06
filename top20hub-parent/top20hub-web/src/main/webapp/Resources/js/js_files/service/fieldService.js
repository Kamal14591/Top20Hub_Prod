app.factory('fieldService', ['$http','$q',function($http,$q){
	
	var Rest_field = '/field/';
	var Rest_field_by_Count = '/field/field_by_Count/';
	var Rest_deletefield = '/field/deleteField/';
	var Rest_updateField = '/field/updateField/';
		
	var factory = {
			fetchAllFields: fetchAllFields,
			createField: createField,
			updateField: updateField,
			deleteField: deleteField,	
			fetchAllFieldsbyCount: fetchAllFieldsbyCount
	}
	return factory;
	
	function fetchAllFieldsbyCount() {
        var deferred = $q.defer();
        $http.get(Rest_field_by_Count)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Fields');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }	
	
	 function fetchAllFields() {
	        var deferred = $q.defer();
	        $http.get(Rest_field)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Fields');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	   function createField(field) {
	        var deferred = $q.defer();
	        $http.post(Rest_field,field)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error to create a field');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	 
	     function updateField(field,id) {
	        var deferred = $q.defer();
	        $http.put(Rest_updateField,field)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating Field:'+id);
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	     
	     
		function deleteField(id) {			
			var deferred = $q.defer();
			$http.delete(Rest_deletefield+id)
			.then(
				function(response){
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while deleting Field');
	                deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
	 
	
}])