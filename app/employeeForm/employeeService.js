angular.module("psApp").factory("employeeService", function($http){

  var _getEmployee = function() {
    return $http.get("localhost:8080/employees");
  };

  var _saveEmployee = function(employee) {
    return $http.post("localhost:8080/employees", employee);
  };


  return {
    getEmployee: _getEmployee,
    saveEmployee: _saveEmployee
  };
});