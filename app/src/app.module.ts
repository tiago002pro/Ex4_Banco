import * as angular from 'angular'
import 'angular-ui-bootstrap'
import 'bootstrap'
import '@uirouter/angularjs'
import './modules'
import base from './components/base/base'
import navBar from './components/nav-bar/nav-bar'
import footer from './components/footer/footer'

angular.module('app', [])
.config(['$stateProvider', ($stateProvider) => {
    $stateProvider
    .state('app', {
        url: '/',
        template: '<h1>Teste</h1>',
    })

}]);