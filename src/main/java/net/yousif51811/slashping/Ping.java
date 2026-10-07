package net.yousif51811.slashping;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ping implements ModInitializer {
	public static final String MOD_ID = "slashping";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					net.minecraft.commands.Commands.literal("ping")
							.executes(context -> {
								ServerPlayer player = context.getSource().getPlayerOrException();
								ping(player);
								return 1;
							})
			);
		});
	}

	private static void ping(ServerPlayer player) {
		int ping = player.connection.latency();
		player.sendSystemMessage(Component.literal("Your ping is " + ping + " ms"));
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
