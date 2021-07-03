package logisticspipes.pipefxhandlers.providers;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import logisticspipes.pipefxhandlers.GenericSparkleFactory;
import logisticspipes.pipefxhandlers.ParticleProvider;

public class EntityLightRedSparkleFXProvider implements ParticleProvider {

	float red = 0.9375F;
	float green = 0.3203125F;
	float blue = 0.30859375F;

	@Override
	public Particle createGenericParticle(ClientWorld world, double x, double y, double z, int amount) {

		return GenericSparkleFactory.getSparkleInstance(world, x, y, z, red, green, blue, amount);

	}

}
