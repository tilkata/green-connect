<script>
    import { onMount } from 'svelte';
    export let postId;
    
    let comments = [];
    let newComment = '';
    let currentPage = 0;
    let totalPages = 1;
    let isLoading = false;

    const fetchComments = async (page = 0) => {
        isLoading = true;
        const response = await fetch(`http://localhost:8070/api/comments/post/${postId}`);
        const data = await response.json();
        comments = [...comments, ...data.content];
        totalPages = data.totalPages;
        isLoading = false;
    };

    const addComment = async () => {
        if (newComment) {
            const response = await fetch(`http://localhost:8070/api/comments/post/${postId}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ text: newComment })
            });
            if (response.ok) {
                newComment = '';
                comments = [];
                currentPage = 0;
                await fetchComments(currentPage);
            }
        }
    };

    const loadMoreComments = async () => {
        if (currentPage < totalPages - 1) {
            currentPage += 1;
            await fetchComments(currentPage);
        }
    };

    onMount(() => {
        fetchComments(currentPage);
    });
</script>

<div class="mt-4">
    <h3 class="text-lg font-bold mb-2">Comments</h3>
    {#each comments as comment}
        <div class="bg-gray-100 p-2 rounded mb-2">{comment.text}</div>
    {/each}
    {#if currentPage < totalPages - 1}
        <button on:click={loadMoreComments} class="btn btn-outline btn-sm mt-2" disabled={isLoading}>
            {#if isLoading} Loading... {/if} Load More
        </button>
    {/if}
    <div class="mt-2 flex">
        <input type="text" bind:value={newComment} placeholder="Add a comment..." class="input input-bordered w-full mr-2" />
        <button on:click={addComment} class="btn btn-primary">Post</button>
    </div>
</div>

<style>
    .comments-section {
        margin-top: 1em;
    }
    .comment {
        margin-bottom: 0.5em;
        padding: 0.5em;
        background: #f9f9f9;
        border-radius: 5px;
    }
</style>
