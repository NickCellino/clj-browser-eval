# Developing with an nREPL and Neovim

To compile the frontend code, run
```bash
bin/compile
```

To startup a REPL, run:
```bash
bin/nrepl
```

Then:
1. Start up Neovim
2. Execute file `nrepl_client`
3. Load up localhost:9000 in the browser

REPL should be all set and connected to browser!

## Notes

The main.js file needs to require cljs.pprint in order for piggieback to not get mad. I don't know why this is, but it be like that.
