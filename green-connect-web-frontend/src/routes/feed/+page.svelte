<script>
    let posts = [
      {
        id: 1,
        username: "john_doe",
        content: "This is my first post!",
        timestamp: "2024-05-21 10:00",
        likes: 10,
        comments: [
          {
            id: 1,
            username: "jane_doe",
            content: "Great post!",
            timestamp: "2024-05-21 11:00"
          },
          {
            id: 2,
            username: "mark_smith",
            content: "Thanks for sharing.",
            timestamp: "2024-05-21 12:00"
          }
        ],
        media: {
          type: "image",
          url: "https://via.placeholder.com/600x400"
        }
      },
      {
        id: 2,
        username: "jane_doe",
        content: "Hello, GreenConnect community!",
        timestamp: "2024-05-21 11:00",
        likes: 5,
        comments: [
          {
            id: 1,
            username: "john_doe",
            content: "Welcome!",
            timestamp: "2024-05-21 11:30"
          }
        ],
        media: {
          type: "video",
          url: "https://www.w3schools.com/html/mov_bbb.mp4"
        }
      }
    ];
  
  </script>
  

  <style>
    .comment-input {
      margin-top: 1rem;
    }
  </style>
  
  <div class="container mx-auto p-4">
    <h1 class="text-4xl font-bold mb-6 text-center">Feed</h1>
    
    {#each posts as post}
      <div class="card bg-base-100 shadow-md mb-6 max-w-2xl mx-auto">
        <div class="card-body">
          <div class="flex items-center mb-4">
            <div class="avatar mr-4">
              <div class="w-12 h-12 rounded-full">
                <img src="https://via.placeholder.com/150" alt="Avatar">
              </div>
            </div>
            <div>
              <h2 class="card-title text-xl font-semibold">{post.username}</h2>
              <p class="text-gray-500 text-sm">{post.timestamp}</p>
            </div>
          </div>
          <p class="mb-4 text-base text-white-800">{post.content}</p>
          
          {#if post.media}
            {#if post.media.type === 'image'}
              <img src={post.media.url} alt="Post Image" class="mb-4 rounded-lg">
            {:else if post.media.type === 'video'}
              <video controls class="mb-4 rounded-lg">
                <source src={post.media.url} type="video/mp4">
                Your browser does not support the video tag.
              </video>
            {/if}
          {/if}
  
          <div class="card-actions justify-between">
            <button class="btn btn-outline btn-primary" on:click={() => {}}>Like ({post.likes})</button>
          </div>
          
          <div class="mt-4">
            <h3 class="text-lg font-semibold mb-2">Comments</h3>
            {#each post.comments as comment}
              <div class="border-t border-gray-300 pt-2">
                <div class="flex items-center mb-2">
                  <div class="avatar mr-2">
                    <div class="w-8 h-8 rounded-full">
                      <img src="https://via.placeholder.com/150" alt="Avatar">
                    </div>
                  </div>
                  <div>
                    <h4 class="font-semibold">{comment.username}</h4>
                    <p class="text-gray-500 text-sm">{comment.timestamp}</p>
                  </div>
                </div>
                <p class="ml-10 mb-2">{comment.content}</p>
              </div>
            {/each}
            <div class="comment-input">
            {#each posts as post}
                <div class="card bg-base-100 shadow-md mb-6 max-w-2xl mx-auto">
                    <div class="card-body">
                        <div class="flex items-center mb-4">
                            <div class="avatar mr-4">
                                <div class="w-12 h-12 rounded-full">
                                    <img src="https://via.placeholder.com/150" alt="Avatar">
                                </div>
                            </div>
                            <div>
                                <h2 class="card-title text-xl font-semibold">{post.username}</h2>
                                <p class="text-gray-500 text-sm">{post.timestamp}</p>
                            </div>
                        </div>
                        <p class="mb-4 text-base text-white-800">{post.content}</p>
                        
                        {#if post.media}
                            {#if post.media.type === 'image'}
                                <img src={post.media.url} alt="Post Image" class="mb-4 rounded-lg">
                            {:else if post.media.type === 'video'}
                                <video controls class="mb-4 rounded-lg">
                                    <source src={post.media.url} type="video/mp4">
                                    Your browser does not support the video tag.
                                </video>
                            {/if}
                        {/if}

                        <div class="card-actions justify-between">
                            <button class="btn btn-outline btn-primary" on:click={() => {}}>Like ({post.likes})</button>
                        </div>
                        
                        <div class="mt-4">
                            <h3 class="text-lg font-semibold mb-2">Comments</h3>
                            {#each post.comments as comment}
                                <div class="border-t border-gray-300 pt-2">
                                    <div class="flex items-center mb-2">
                                        <div class="avatar mr-2">
                                            <div class="w-8 h-8 rounded-full">
                                                <img src="https://via.placeholder.com/150" alt="Avatar">
                                            </div>
                                        </div>
                                        <div>
                                            <h4 class="font-semibold">{comment.username}</h4>
                                            <p class="text-gray-500 text-sm">{comment.timestamp}</p>
                                        </div>
                                    </div>
                                    <p class="ml-10 mb-2">{comment.content}</p>
                                </div>
                            {/each}
                            <div class="comment-input">
                                let inputEl;
                                                                <input type="text" placeholder="Add a comment..." class="input input-bordered w-full">
                            </div>
                        </div>
                    </div>
                </div>
            {/each}
            </div>
          </div>
        </div>
      </div>
    {/each}
  </div>
  