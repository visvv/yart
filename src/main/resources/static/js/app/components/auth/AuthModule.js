var authModule = angular.module('AuthModule',[]);

var AuthenticationService = function(http){
	return {
		authenticate : function(username, password, callback){
			console.log(username, password);
			if(username === 'mail@mail.com' && password === 'pwd'){
				callback(undefined,{status : true});
				return true;
			}else{
				callback({status:false});
				return false;
			}
		}
	};
}

var LoginController = function(scope,authenticationService){
	this.scope = scope;
	this.authenticationService = authenticationService;
	scope.loginController = this;
	scope.username = '';
	scope.password = '';
	scope.message = '';
};

LoginController.prototype.login = function(){
	var self = this;
	if(this.scope.username === '' || this.scope.password === ''){
		return false;
	}
	this.authenticationService.authenticate(this.scope.username,this.scope.password, function(err, data){
		if(err){
			console.log('Invalid login');
			self.scope.message = 'Invalid email/password';
		}else{
			console.log('Login success');
		}
	});
};
authModule.factory('authenticationService', ['$http',AuthenticationService]);
authModule.controller('loginController', ['$scope','authenticationService',LoginController]);

angular.element(document).ready(function(){
	$('#myModal').modal('show');
});