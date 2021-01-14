<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

// News routes
Route::resource('news', \App\Http\Controllers\NewsController::class);
//route for the api
Route::get('thenews/q={id:title}', 'App\Http\Controllers\NewsController@buscar');
Route::get('thenews/pageSize={num}', 'App\Http\Controllers\NewsController@cantidad');
Route::get('thenews/pageSize', 'App\Http\Controllers\NewsController@canti');
Route::get('thenews/pageSize=', 'App\Http\Controllers\NewsController@canti');
