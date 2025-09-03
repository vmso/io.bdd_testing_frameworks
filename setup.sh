#!/bin/bash

# Modern BDD Testing Framework Setup Script
# This script sets up the testing environment and configuration

set -e  # Exit on any error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Function to check Java version
check_java_version() {
    if command_exists java; then
        JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
        if [ "$JAVA_VERSION" -ge 17 ]; then
            print_success "Java $JAVA_VERSION found"
        else
            print_error "Java 17 or higher is required. Found version: $JAVA_VERSION"
            exit 1
        fi
    else
        print_error "Java is not installed"
        exit 1
    fi
}

# Function to check Maven version
check_maven_version() {
    if command_exists mvn; then
        MAVEN_VERSION=$(mvn -version 2>&1 | head -n 1 | cut -d' ' -f3)
        print_success "Maven $MAVEN_VERSION found"
    else
        print_error "Maven is not installed"
        exit 1
    fi
}

# Function to check Docker
check_docker() {
    if command_exists docker; then
        print_success "Docker found"
        if command_exists docker-compose; then
            print_success "Docker Compose found"
        else
            print_warning "Docker Compose not found. Installing..."
            # Install Docker Compose if not present
            sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
            sudo chmod +x /usr/local/bin/docker-compose
            print_success "Docker Compose installed"
        fi
    else
        print_error "Docker is not installed"
        exit 1
    fi
}

# Function to create directories
create_directories() {
    print_status "Creating necessary directories..."
    
    mkdir -p logs
    mkdir -p reports
    mkdir -p reports/cucumber-reports
    mkdir -p reports/allure-reports
    mkdir -p test-data
    mkdir -p wiremock/mappings
    mkdir -p sqlQueries
    
    print_success "Directories created"
}

# Function to setup configuration
setup_configuration() {
    print_status "Setting up configuration..."
    
    if [ ! -f "config.properties" ]; then
        if [ -f "config.properties.template" ]; then
            cp config.properties.template config.properties
            print_success "Configuration file created from template"
            print_warning "Please update config.properties with your specific values"
        else
            print_error "Configuration template not found"
            exit 1
        fi
    else
        print_warning "Configuration file already exists"
    fi
}

# Function to setup environment variables
setup_environment() {
    print_status "Setting up environment variables..."
    
    # Create .env file if it doesn't exist
    if [ ! -f ".env" ]; then
        cat > .env << EOF
# Environment variables for BDD Testing Framework
# Update these values according to your environment

# Test Environment
TEST_ENVIRONMENT=development
TEST_PARALLEL_THREADS=4
TEST_TIMEOUT=30

# Browser Configuration
TEST_DEFAULT_BROWSER=chrome
TEST_BASE_URL=https://example.com
TEST_HEADLESS=false

# Mobile Configuration
TEST_APPIUM_URL=http://localhost:4723
TEST_MOBILE_PLATFORM=android

# API Configuration
TEST_API_BASE_URL=https://api.example.com
TEST_SSL_VERIFY=true
TEST_API_AUTH_TYPE=none

# Database Configuration
TEST_CONNECTION_STRING=jdbc:postgresql://localhost:5432/testdb
TEST_DB_USER=testuser
TEST_DB_PASSWORD=testpass

# Slack Configuration (optional)
# TEST_SLACK_TOKEN=your_slack_token
# TEST_WEBHOOK=your_webhook_url

# Cloud Testing Configuration (optional)
# TEST_CLOUD_PROVIDER=browserstack
# TEST_CLOUD_USERNAME=your_username
# TEST_CLOUD_ACCESS_KEY=your_access_key

# Monitoring Configuration
TEST_MONITORING=false
TEST_METRICS_ENDPOINT=http://localhost:9090
TEST_GRAFANA_URL=http://localhost:3000

# Performance Testing
TEST_PERFORMANCE=false

# Security Scanning
TEST_SECURITY_SCAN=false

# WireMock Configuration
TEST_WIREMOCK_ENABLED=false
TEST_WIREMOCK_URL=http://localhost:8080

# Test Data Configuration
TEST_DATA_GENERATION=true
TEST_DATA_LOCALE=en_US

# Advanced Configuration
TEST_VIDEO_RECORDING=false
TEST_NETWORK_THROTTLING=false
TEST_NETWORK_SPEED=fast
TEST_GEOLOCATION=false
TEST_GEOLOCATION_COORDS=40.7128,-74.0060
EOF
        print_success "Environment file (.env) created"
        print_warning "Please update .env file with your specific values"
    else
        print_warning "Environment file (.env) already exists"
    fi
}

# Function to build the project
build_project() {
    print_status "Building the project..."
    
    # Clean and compile
    mvn clean compile -DskipTests=true
    
    if [ $? -eq 0 ]; then
        print_success "Project built successfully"
    else
        print_error "Project build failed"
        exit 1
    fi
}

# Function to start Docker services
start_docker_services() {
    print_status "Starting Docker services..."
    
    # Start core services
    docker-compose up -d selenium-hub chrome firefox postgres redis
    
    # Wait for services to be ready
    print_status "Waiting for services to be ready..."
    sleep 30
    
    # Check if services are running
    if docker-compose ps | grep -q "Up"; then
        print_success "Docker services started successfully"
    else
        print_error "Failed to start Docker services"
        exit 1
    fi
}

# Function to run initial tests
run_initial_tests() {
    print_status "Running initial tests to verify setup..."
    
    # Run a simple test to verify everything works
    mvn test -Dtest=TestRunner -pl api_testing -q
    
    if [ $? -eq 0 ]; then
        print_success "Initial tests passed"
    else
        print_warning "Initial tests failed - this might be expected if no tests exist yet"
    fi
}

# Function to display next steps
display_next_steps() {
    echo ""
    echo "=========================================="
    echo "🎉 Setup completed successfully!"
    echo "=========================================="
    echo ""
    echo "Next steps:"
    echo "1. Update config.properties with your specific values"
    echo "2. Update .env file with your environment variables"
    echo "3. Start Docker services: docker-compose up -d"
    echo "4. Run tests: mvn test"
    echo "5. View reports: open reports/cucumber-reports/index.html"
    echo ""
    echo "Useful commands:"
    echo "- Run API tests: mvn test -pl api_testing"
    echo "- Run Web tests: mvn test -pl web_testing"
    echo "- Run Mobile tests: mvn test -pl mobile_testing"
    echo "- Run with parallel execution: mvn test -Dparallel.threads=4"
    echo "- Generate reports: mvn allure:report"
    echo ""
    echo "Documentation: README.md"
    echo "Issues: GitHub Issues"
    echo ""
}

# Main setup function
main() {
    echo "=========================================="
    echo "🚀 Modern BDD Testing Framework Setup"
    echo "=========================================="
    echo ""
    
    # Check prerequisites
    print_status "Checking prerequisites..."
    check_java_version
    check_maven_version
    check_docker
    
    # Setup environment
    create_directories
    setup_configuration
    setup_environment
    
    # Build project
    build_project
    
    # Start services (optional)
    read -p "Do you want to start Docker services now? (y/n): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        start_docker_services
    fi
    
    # Run initial tests (optional)
    read -p "Do you want to run initial tests? (y/n): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        run_initial_tests
    fi
    
    # Display next steps
    display_next_steps
}

# Run main function
main "$@" 