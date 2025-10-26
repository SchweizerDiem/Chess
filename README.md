# ♟️ Chess — Play, Learn, Dominate

Welcome to Chess — a clean, fast, and dope chess project built to play, study, and tinker with the royal game. Whether you're building a GUI, a web app, an engine, or an AI opponent, this repo is your launchpad. This README gives you the swaggered-up elevator pitch, quickstart, and contribution guidelines so you (and your future collaborators) can jump in and ship.

## Badges
- build: [![Build Status](https://img.shields.io/badge/build-pending-lightgrey)]()
- license: [![License: MIT](https://img.shields.io/badge/license-MIT-blue)]()
- contributors: [![Contributors](https://img.shields.io/badge/contributors-welcome-brightgreen)]()

## TL;DR
- Fast to set up. Clear code. Beautiful board.
- Play human vs human (local).
- Perfect for learning chess programming, testing AI, or adding a sleek UI.

# Why this project
- Minimal, readable code so you can learn chess programming patterns quickly.
- Modular engine + UI separation for easy experimentation.
- Built to be extended: add features like PGN import/export, move suggestions, ELO tracking, or networked multiplayer.

# Features
- Standard chess rules (castling, en passant, pawn promotion, draw detection)
- Move validation and legal-move generation
- Game state serialization (FEN / PGN support placeholder)
- Pluggable engine interface (hook your AI easily)
- Tests for core engine components (move generator, legal-state checks)
- Friendly dev experience: lint scripts, test suite, CI-ready structure

# Table of Contents
- Quick Start
- Installation
- Contributing
- Roadmap
- License & Contact

## Quick start (3 minutes)
1) Clone:
   git clone https://github.com/SchweizerDiem/Chess.git
   cd Chess

2) Install dependencies and run:
```bash
gradle build
```

## Contributing — help make this legendary
- Found a bug? Open an issue with steps to reproduce and a minimal test case.
- Want to add a feature? Open an issue to discuss scope first.
- Ready to contribute code? Fork → branch → PR. Follow these guidelines:
  - Use a descriptive branch name: feature/<short-desc> or fix/<short-desc>
  - Write tests for behavior you add/change
  - Keep changes focused and well-documented
  - Run the test suite and linters before submitting

## Code style
- Keep functions small and well-named
- Document exported modules/classes with docstrings or JSDoc/TSDoc
- Include tests for edge cases (e.g. promotion, en passant, stalemate)

## Roadmap (ideas — pick what you want)
- Multiplayer over WebRTC / WebSocket
- Opening book support & built-in engines (Stockfish integration)
- Training modes (tactics puzzles, endgame drills)
- ELO tracking, player profiles, and leaderboards
- Mobile responsive UI and offline-first support

# License
- This repo is licensed under the MIT License — play with it, build on it, give credit where it's due.

# Acknowledgements
- Thanks to the open-source chess community, authors of core algorithms (move generation, evaluation), and the many tutorials and papers that inspired this project.
