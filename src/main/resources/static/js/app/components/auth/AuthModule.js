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
};

var LoginViewService = function(){
	return{
		showLoginBox : function(){
			$('#signUpModal').modal('hide');
			$('#loginModal').modal('show');
		},
		showSignUpBox : function(){
			$('#loginModal').modal('hide');
			$('#signUpModal').modal('show');
		}
	};
};

var LoginController = function(scope,authenticationService,loginViewService){
	this.scope = scope;
	this.authenticationService = authenticationService;
	this.loginViewService = loginViewService;
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
LoginController.prototype.showLoginBox = function(){
	this.loginViewService.showLoginBox();
};

LoginController.prototype.showSignUpBox = function(){
	this.loginViewService.showSignUpBox();
};


authModule.factory('authenticationService', ['$http',AuthenticationService]);
authModule.factory('loginViewService', [LoginViewService]);

authModule.controller('loginController', ['$scope','authenticationService','loginViewService',LoginController]);

angular.element(document).ready(function(){
	LoginViewService().showLoginBox();
});