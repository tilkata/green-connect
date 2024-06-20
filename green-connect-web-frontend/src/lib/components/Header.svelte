<script>
  import img from '$lib/assets/logo-pic.png';
</script>

<header class="bg-green-500 text-white p-4 flex justify-between items-center shadow-lg">
  <div class="flex items-center">
    <img class="w-18 h-16 mr-4" src={img} alt="Green Connect logo" />
    <div>
      <h1 class="text-3xl font-bold">GreenConnect</h1>
      <p class="text-sm font-light">Connecting People for a Sustainable Future</p>
    </div>
  </div>
  <nav class="space-x-4">
    <a class="btn btn-ghost text-white" href="/">Home</a>
    <a class="btn btn-ghost text-white" href="/login">Login</a>
    <a class="btn btn-ghost text-white" href="/register">Register</a>
    <a class="btn btn-ghost text-white" href="/panel">Panel</a>
    <a class="btn btn-ghost text-white" href="/userDetails">Details</a>
    <a class="btn btn-ghost text-white" href="/privacy-policy">Privacy Policy</a>
    <a class="btn btn-ghost text-white" href="/feed">Feed</a>
  </nav>
</header>
