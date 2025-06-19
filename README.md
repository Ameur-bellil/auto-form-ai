# Auto Form AI

<div align="center">

![Auto Form AI Logo](https://img.shields.io/badge/Auto%20Form%20AI-Intelligent%20CSV%20Processing-blue)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-8%2B-orange)](https://www.java.com/)

</div>

## 📋 Overview

Auto Form AI is a sophisticated Java-based application that leverages language models (LLMs) to intelligently populate empty fields in CSV files. The system analyzes the provided data structure, sends it to a language model with specific completion prompts, and then saves the enriched data back to the original file format.

## ✨ Features

- **Intelligent CSV Processing**: Automatically detects and fills empty fields in CSV files using AI
- **Custom Field Support**: Specifically handles personal information fields (FirstName, LastName, E-mail, Age)
- **API Integration**: Seamlessly communicates with language model APIs (compatible with Ollama and similar services)
- **Data Preservation**: Maintains original file structure while enhancing content
- **Simple Implementation**: Easy-to-understand codebase with minimal dependencies

## 🔧 Prerequisites

- **Java Development Kit**: JDK 8 or higher
- **Dependencies**: 
  - org.json library (version 20231013 or newer)
- **LLM API Access**: A running instance of an LLM API
  - Default configuration works with Ollama at `http://localhost:11434/api/generate`
  - Compatible with models like deepseek-r1:7b
- **Input Data**: Valid CSV files with fields to be populated

## 📥 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/ameur-bellil/auto-form-ai.git
   cd auto-form-ai
   ```

2. **Add required dependencies**

   For Maven projects, add to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.json</groupId>
       <artifactId>json</artifactId>
       <version>20231013</version>
   </dependency>
   ```

   For Gradle projects, add to your `build.gradle`:
   ```gradle
   implementation 'org.json:json:20231013'
   ```

3. **Configure LLM API endpoint**

   The default configuration points to `http://localhost:11434/api/generate`. If your LLM API is hosted elsewhere, update the `sendPromptToLLM` method in the `Main.java` file with the appropriate URL.

## 🚀 Usage

1. **Prepare your CSV file**

   Place your input CSV file (e.g., `CSVFile.csv`) in the specified directory.

2. **Compile and run the program**

   ```bash
   # Compile
   javac -cp .:path/to/json.jar org/example/Main.java
   
   # Run
   java -cp .:path/to/json.jar org.example.Main
   ```

3. **Automated Process Flow**

   The program will:
   - Read the specified CSV file
   - Send the content to the configured LLM with appropriate prompts
   - Process the AI-generated responses and extract the filled data
   - Save the completed CSV content back to the original file

## 📊 Example

**Input CSV:**
```csv
FirstName,LastName,E-mail,Age
,,, 
,,, 
```

**Output CSV:**
```csv
FirstName,LastName,E-mail,Age
John,Doe,john.doe@example.com,30
Amer,bellil,amer.bellil@example.com,50
```

## 📁 Project Structure

```
auto-form-ai/
├── CSVFile.csv                # Example input/output CSV file
├── src/                       # Source code directory
│   └── org/example/
│       └── Main.java          # Application entry point and implementation
├── README.md                  # Project documentation
└── pom.xml                    # Maven project configuration
```

## 🛠️ Configuration Options

The current implementation uses hardcoded values for several parameters:

- **File Path**: Set directly in the code (default: `/home/amer/Desktop/protos/auto-form-ai/`)
- **CSV Structure**: Expects headers `FirstName,LastName,E-mail,Age`
- **LLM API Endpoint**: Default `http://localhost:11434/api/generate`

**Potential Enhancements:**
- Make file paths configurable via command-line arguments
- Support custom field mapping for different CSV structures
- Add configuration file for API endpoints and model parameters

## ⚠️ Notes and Limitations

- Ensure the LLM API is running and accessible before using the application
- The application currently expects a specific CSV structure with predefined headers
- Response quality depends on the capabilities of the connected language model
- Consider implementing proper error handling for production use cases

## 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Contributors

- [Ameur Bellil](https://github.com/ameur-bellil) - Creator and Maintainer

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
