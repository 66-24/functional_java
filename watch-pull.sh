#!/bin/bash

# Step 1: Ensure our list of remote branches is up-to-date
# Prune cleans up dangling local reference to remote branches that have been deleted
echo "Fetching latest data from all remotes..."
git fetch --all --prune
echo

# Step 2: Use fzf to select a remote branch
# 'git branch -r' lists remote branches (e.g., "upstream/java-aug2025")
# We pipe this list into fzf for interactive selection.
selected_branch=$(git branch -r | sed 's/^[ \t]*//' | fzf --prompt="Select a branch to watch > " --height=20%)

# Step 3: Exit if the user cancelled fzf (e.g., by pressing Esc)
if [ -z "$selected_branch" ]; then
    echo "No branch selected. Exiting."
    exit 0
fi

# Step 4: Parse the selection to get the remote and branch name separately
# Example: "upstream/java-aug2025" -> remote="upstream", branch="java-aug2025"
remote=$(echo "$selected_branch" | cut -d'/' -f1)
branch=$(echo "$selected_branch" | cut -d'/' -f2-)

# Step 5: Ask the user for the update interval
read -p "Enter update interval in seconds (e.g., 60): " interval

# Validate that the input is a number, default to 60 if not.
if ! [[ "$interval" =~ ^[0-9]+$ ]]; then
    echo "Invalid interval. Defaulting to 60 seconds."
    interval=60
fi

# Step 6: Build and run the final watch command
echo "Starting watch. Press Ctrl+C to stop."
sleep 2

# The variables are expanded here before watch is executed.
watch -n "$interval" "echo \"[\$(date +'%T')] Pulling from ${remote}/${branch}...\" && git pull ${remote} ${branch}"
