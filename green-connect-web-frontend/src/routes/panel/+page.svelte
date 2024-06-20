<script>
    import { onMount } from 'svelte';

    let username = '';
    let password = '';
    let email = '';
    let fullName = '';
    let profilePictureUrl = '';
    let bio = '';
    let birthdate = '';
    let address = '';
    let city = '';
    let country = '';
    let consentGiven = false;

    let userId = '';
    let postContent = '';
    let postTags = '';
    let postCategory = '';
    let postVisibility = 'PUBLIC'; // default value
    let postType = '';
    let imageFile;

    let errorMessage = '';
    let successMessage = '';

    async function createAccount() {
        errorMessage = '';
        successMessage = '';
        try {
            const response = await fetch('http://localhost:8009/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username, password, email, fullName, profilePictureUrl, bio, birthdate, address, city, country, consentGiven
                })
            });
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`API error: ${response.status} ${response.statusText} - ${errorText}`);
            }
            console.log(await response.json());
            successMessage = 'Account created successfully!';
        } catch (error) {
            if (error instanceof Error) {
                errorMessage = 'Failed to create account: ' + error.message;
            } else {
                errorMessage = 'Failed to create account: An unknown error occurred';
            }
            console.error('Create account error:', error);
        }
    }

    async function deleteUser() {
        errorMessage = '';
        successMessage = '';
        try {
            const response = await fetch(`http://localhost:8009/users/${userId}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error(`API error: ${response.statusText}`);
            }

            // Check if the response has a body and its length is greater than 0
            const text = await response.text();
            if (text.length > 0) {
                const json = JSON.parse(text);
                console.log(json);
            } else {
                console.log('User deleted successfully, no response body');
            }
            successMessage = 'User deleted successfully!';
        } catch (error) {
            if (error instanceof Error) {
                errorMessage = 'Failed to delete user: ' + error.message;
            } else {
                errorMessage = 'Failed to delete user: An unknown error occurred';
            }
            console.error('Delete user error:', error);
        }
    }

    async function uploadPost() {
        errorMessage = '';
        successMessage = '';
        try {
            const formData = new FormData();
            formData.append('postDTO', JSON.stringify({
                authorId: userId,
                contentIdentifier: 'updated-azure-blob-identifier',
                contentText: postContent,
                tags: postTags.split(','),
                category: postCategory,
                visibility: postVisibility,
                contentType: postType
            }));
            formData.append('userId', userId);
            formData.append('file', imageFile);

            const response = await fetch('http://localhost:8090/api/posts/upload', {
                method: 'POST',
                body: formData,
            });
            if (!response.ok) {
                throw new Error(`API error: ${response.statusText}`);
            }
            console.log(await response.json());
            successMessage = 'Post uploaded successfully!';
        } catch (error) {
            if (error instanceof Error) {
                errorMessage = 'Failed to upload post: ' + error.message;
            } else {
                errorMessage = 'Failed to upload post: An unknown error occurred';
            }
            console.error('Upload post error:', error);
        }
    }
</script>

<main class="p-6 space-y-6 bg-base-200">
    {#if errorMessage}
        <div class="alert alert-error shadow-lg">
            <div>
                <span>{errorMessage}</span>
            </div>
        </div>
    {/if}
    
    {#if successMessage}
        <div class="alert alert-success shadow-lg">
            <div>
                <span>{successMessage}</span>
            </div>
        </div>
    {/if}

    <div class="flex flex-wrap justify-between gap-6">
        <div class="bg-blue-500 p-6 rounded-lg shadow-lg flex-1 min-w-300px">
            <h2 class="text-2xl font-bold mb-4 text-white">Create Account</h2>
            <form on:submit|preventDefault={createAccount} class="space-y-4">
                <input class="input input-bordered w-full" placeholder="Username" bind:value={username} />
                <input class="input input-bordered w-full" type="password" placeholder="Password" bind:value={password} />
                <input class="input input-bordered w-full" type="email" placeholder="Email" bind:value={email} />
                <input class="input input-bordered w-full" placeholder="Full Name" bind:value={fullName} />
                <input class="input input-bordered w-full" placeholder="Profile Picture URL" bind:value={profilePictureUrl} />
                <input class="input input-bordered w-full" placeholder="Bio" bind:value={bio} />
                <input class="input input-bordered w-full" type="date" placeholder="Birthdate" bind:value={birthdate} />
                <input class="input input-bordered w-full" placeholder="Address" bind:value={address} />
                <input class="input input-bordered w-full" placeholder="City" bind:value={city} />
                <input class="input input-bordered w-full" placeholder="Country" bind:value={country} />
                <label class="flex items-center space-x-2">
                    <input type="checkbox" bind:checked={consentGiven} class="checkbox" />
                    <span class="text-white">Consent Given</span>
                </label>
                <button class="btn btn-primary w-full" type="submit">Create Account</button>
            </form>
        </div>

        <div class="bg-red-500 p-6 rounded-lg shadow-lg flex-1 min-w-300px">
            <h2 class="text-2xl font-bold mb-4 text-white">Delete User</h2>
            <form on:submit|preventDefault={deleteUser} class="space-y-4">
                <input class="input input-bordered w-full" placeholder="User ID" bind:value={userId} />
                <button class="btn btn-danger w-full" type="submit">Delete User</button>
            </form>
        </div>

        <div class="bg-green-500 p-6 rounded-lg shadow-lg flex-1 min-w-300px">
            <h2 class="text-2xl font-bold mb-4 text-white">Upload Post</h2>
            <form on:submit|preventDefault={uploadPost} class="space-y-4">
                <input class="input input-bordered w-full" placeholder="User ID" bind:value={userId} />
                <input class="input input-bordered w-full" placeholder="Post Content" bind:value={postContent} />
                <input class="input input-bordered w-full" placeholder="Post Tags (comma separated)" bind:value={postTags} />
                <input class="input input-bordered w-full" placeholder="Post Category" bind:value={postCategory} />
                <select class="select select-bordered w-full" bind:value={postVisibility}>
                    <option value="PUBLIC">PUBLIC</option>
                    <option value="PRIVATE">PRIVATE</option>
                    <option value="FRIENDS_ONLY">FRIENDS_ONLY</option>
                </select>
                <input class="input input-bordered w-full" placeholder="Post Type" bind:value={postType} />
                <input type="file" class="file-input w-full" accept="image/png" on:change={e => imageFile = e.target.files[0]} />
                <button class="btn btn-success w-full" type="submit">Upload Post</button>
            </form>
        </div>
    </div>
</main>

<style>
    main {
        max-width: 1200px;
        margin: auto;
    }
    .min-w-300px {
        min-width: 300px;
    }
</style>

  