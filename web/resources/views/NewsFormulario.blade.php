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

    <form action="{{url('News/Agregar')}}" method="post">
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
            <label for="url">url</label>
            <input type="text" class="form-control" name="url">
        </div>

        <br>
        <div class="form-group">
            <label for="url_image">url(imagen)</label>
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
        <div class="container">

            <label for="published_at">published_at</label>

            <input class="date form-control" type="text" name="published_at">


        </div>
        <br>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
</div>
<script type="text/javascript">

    $('.date').datepicker({

        format: 'mm-dd-yyyy'

    });

</script>
</body>
</html>
