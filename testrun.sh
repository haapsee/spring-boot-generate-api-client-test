# kill $(lsof -t -i:8080)

mvn clean install

# Define cleanup function to kill the backend process
cleanup() {
  echo "Shutting down backend server..."
  if [ -n "$BACKEND_PID" ]; then
    kill $BACKEND_PID
    wait $BACKEND_PID 2>/dev/null
    echo "Backend server stopped"
  fi
}

# Set trap to ensure backend is killed when script exits
trap cleanup EXIT INT TERM

# Run backend in the background
java -jar backend/target/springboot-1.0-SNAPSHOT.jar &
BACKEND_PID=$!

# Wait for backend to become healthy
echo "Waiting for backend to become healthy..."
while ! curl -s http://localhost:8080/ > /dev/null; do
  sleep 1
  echo "Still waiting for backend..."
  # Check if backend process is still running
  if ! ps -p $BACKEND_PID > /dev/null; then
    echo "Error: Backend process has died"
    exit 1
  fi
done
echo "Backend is healthy!"

# Run application in the foreground
java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar