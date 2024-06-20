<script>
    import { onMount } from 'svelte';
    import User from '$lib/components/User.svelte';
    import Comment from '$lib/components/Comment.svelte';

    export let post;

    let user = null;

    onMount(async () => {
        const response = await fetch(`http://localhost:8009/users/${post.authorId}`);
        user = await response.json();
    });
</script>

<article class="card w-96 bg-base-100 shadow-xl mb-6">
    <div class="card-body p-4">
        {#if user}
            <User {user} class="mb-2" />
        {/if}
        <figure class="mb-4">
            <img src={post.contentIdentifier} alt="Post Image" class=" w-full h-64 object-fit" />
        </figure>
        <p class="mb-4">{post.contentText}</p>
        <div class="mb-2">
            <strong>Tags:</strong>
            {#each post.tags as tag}
                <span class="badge badge-primary ml-1">{tag}</span>
            {/each}
        </div>
        <div class="flex items-center">
            <button class="btn btn-sm btn-outline mr-2">
                <i class="fa fa-thumbs-up mr-2"></i> {post.likeCount}
            </button>
            <Comment postId={post.id} class="flex-grow" />
        </div>
    </div>
</article>

