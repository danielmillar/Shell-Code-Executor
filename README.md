# Shell Code Executor

![Tested Versions](https://img.shields.io/badge/Tested%20on-1.19.x-brightgreen?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/danielmillar/Shell-Code-Executor?color=bright_green&style=for-the-badge)


Shell Code Executor is a powerful and versatile Spigot plugin that allows server administrators to execute shell commands directly from within the game. Designed with flexibility in mind, the plugin provides an easy-to-use interface for running system-level tasks without leaving the Minecraft environment.

### Features

* Execute shell commands from the in-game chat or console with a simple command syntax.
* Execute shell commands from inside a book by using the shellbook command.
* Redirect command output to the in-game chat or server console based on your preference.
* Permission-based access control ensures that only authorized users can execute shell commands.
* Fully customizable configuration for fine-grained control over plugin settings.
* Supports various platforms, including Linux, and Windows servers.

### Commands

| Command                     | Description                                               |
|-----------------------------|-----------------------------------------------------------|
| `/shell [output] <command>` | Execute a shell command from the in-game chat or console. |
| `/shellbook`                | Execute a shell command from inside a book.               |
| `/shellreload`              | Reload the plugins config.                                |


### Command Arguments

| Arguments | Description                                               |
|-----------|-----------------------------------------------------------|
| default   | By default it'll output using `-u` so in-game chat (User) |
| `-u`      | Output the command output to in-game chat (User)          |
| `-c`      | Output the command output to server console chat (User)   |

### Permissions

| Permission      | Description                                               |
|-----------------|-----------------------------------------------------------|
| `shell.execute` | Allows a user to execute shell commands using the plugin. |
| `shell.reload`  | Allows a user to reload the plugins config.               |

### TODO

- [x] Make messages configurable
- [ ] Allow books to have their own process

### Disclaimer

Please be aware that running shell commands from a Spigot plugin can be dangerous and is not recommended for inexperienced users or in public server environments. This plugin should be used with extreme caution and only by server administrators who understand the potential risks involved.

By using this plugin, you acknowledge that you are responsible for any damage or security breaches that may result from its use. We are not liable for any harm caused by its operation.