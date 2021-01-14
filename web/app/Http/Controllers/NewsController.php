<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;
use Carbon\Carbon;

class NewsController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        // SELECT * FROM News
        $news = News::all();

        // Return the GET request with code 200
        return response([
            'message' => 'Retrieved Successfully',
            'news' => $news

        ],200);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //return the view
        return view('NewsFormulario');
    }

    /**
     * Store a newly created news
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $cosa = Carbon::now()->format('Y-m-d\TH:i:s.ZZZZZZ\Z');


        $this->validate($request,[
            'title' => 'required',
            'author' => 'required',
            'url' => 'required',
            'url_image' => 'nullable',
            'description' => 'required',
            'content' => 'required',
            'published_at' => 'nullable',
        ]);
        News::create([
            'title' => $request->get('title'),
            'author' => $request->get('author'),
            'url' => $request->get('url'),
            'url_image' => $request->get('url_image'),
            'description' => $request->get('description'),
            'content' => $request->get('content'),
            'published_at' => $cosa
        ]);


        return back()->with('mensaje','Haz agregado una Noticia');

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * search the news with the title
     * @param News $id in these case is the title
     * @return \Illuminate\Http\JsonResponse
     */
    public function buscar(news $id)
    {
        $newss = News::find($id,['title','author','url','url_image','description','content','published_at']);
        return response()->json($newss,200);
    }

    /**
     * return news if the number is correct
     * @param $num number of the news to return
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\Routing\ResponseFactory|\Illuminate\Http\JsonResponse|\Illuminate\Http\Response
     */
    public function cantidad($num)
    {

        if($num < 101 && $num > 0){
            $news = News::all(['title','author','url','url_image','description','content','published_at'])->take($num);
            return response()->json($news,200);

        }else{
            return response([
                'message' => 'Error the number is bad',
            ],400);
        }

    }

    /**
     * return 20 news if you didnt give a number.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function canti()
    {
        $news = News::all(['title','author','url','url_image','description','content','published_at'])->take(20);
        return response()->json($news,200);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
