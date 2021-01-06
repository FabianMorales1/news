<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateNewsTable extends Migration
{
    /**
     * Run the migrations for the news.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('news',
            function (Blueprint $table) {
                $table->id();
                $table->string("title");
                $table->string("author");
                $table->string("url");
                $table->string("url_image")->nulleable();
                $table->string("description");
                $table->string("content");
                $table->timestamp("published_at");
                $table->timestamps();

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('news');
    }
}
