package net.fabricmc.example;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.Session;



public class DougMod implements ModInitializer {
	public static final Client client = new Client();

	@Override
	public void onInitialize() {
		client.init();
		
		System.out.println("Hello Fabric world!");
	}
}
