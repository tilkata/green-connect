<script>
  import { onMount } from 'svelte';
  import Post from '$lib/components/Post.svelte';
  import User from '$lib/components/User.svelte';

  let posts = [];
  let users = [];

  onMount(async () => {
      const postsResponse = await fetch('http://localhost:8090/api/posts');
      posts = await postsResponse.json();
      
      const usersResponse = await fetch('http://localhost:8009/users');
      users = await usersResponse.json();
  });
</script>

<main class="p-4 bg-gray-100 min-h-screen">
  <h1 class="text-4xl text-center mb-6">Feed</h1>
  <div class="flex">
    <div class="w-2/4 max-w-md mx-auto space-y-4">
      <h2 class="text-2xl text-center mb-4">Users</h2>
      {#each users as user}
        <User {user} />
      {/each}
    </div>
    <div class="w-2/4 max-w-3xl mx-auto space-y-4">
      {#each posts as post}
        <div class="post-container">
          <Post {post} />
        </div>
      {/each}
    </div>
  </div>
</main>
