# XArmor

## About
Download latest release [here](https://github.com/valkyrienyanko/XArmor/releases).

Only 25% of the plugin works, please see [this issue](https://github.com/valkyrienyanko/XArmor/issues/1) to find out why.

Different colored leather armors give different perks.

The more armor pieces you have the greater the effect.

### Per Armor Piece Effects
- Green = Health Boost
- Light Blue = Damage Resistance
- White = Damage Resistance + Health Boost + Set Chance to Regenerate on Hit
- Orange = Set Chance to Increase Damage on Hit
- Black = Slow + Confuse Attacker on Hit

### Full Armor Set Effects
- Orange = Fire Resistance

Config
```yml
# Default config value, if you're not using a permissions plugin then enable this.
ignorePermissions: false
```

## Compiling
1. Clone the repository directly into your IDE.
2. Build Spigot with [Spigot's Build Tools](https://www.spigotmc.org/wiki/buildtools/) then add the JAR to the projects build path.
3. Fix any outdated code in the project depending on what version of Spigot you installed.
4. Compile the plugin by exporting it to a JAR file.
