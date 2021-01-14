<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="{{asset('css/app.css')}}">
    <title>Formulario</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css"/>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
</head>
<body>
<div class="container">
    <br/>
    @if($errors->any())
        <div class="alert alert-danger">
            <ul>
                @foreach($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif

    @if(session('mensaje'))
        <div class="alert alert-success">
            <p>{{ session('mensaje')  }}</p>
        </div>
    @endif

    <form action="{{url('news/add')}}" method="post">
        @csrf
        <div class="form-group">
            <label for="title">Titulo</label>
            <input type="text" class="form-control" name="title">
        </div>
        <br>
        <div class="form-group">
            <label for="author">autor</label>
            <input type="text" class="form-control" name="author">
        </div>
        <br>
        <div class="form-group">
            <label for="url">url(ej. https://www.coindesk.com/podcasts/on-purpose-with-tyrone-ross/the-bitcoin-opportunity-is-right-here-in-america" )</label>
            <input type="text" class="form-control" name="url">
        </div>

        <br>
        <div class="form-group">
            <label for="url_image">url(ej. https://static.coindesk.com/wp-content/uploads/2020/10/On-Purpose-Social_1200x628-1.jpg)</label>
            <input type="text" class="form-control" name="url_image">
        </div>
        <br>
        <div class="form-group">
            <label for="description">descripcion</label>
            <input type="text" class="form-control" name="description">
        </div>
        <br>
        <div class="form-group">
            <label for="content">contenido</label>
            <input type="text" class="form-control" name="content">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">PUBLICAR NOTICIA</button>
    </form>
</div>
</body>
</html>





