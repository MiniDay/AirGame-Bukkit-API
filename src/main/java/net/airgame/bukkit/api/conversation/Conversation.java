package net.airgame.bukkit.api.conversation;

import net.airgame.bukkit.api.AirGamePlugin;
import net.airgame.bukkit.api.util.AirGameUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("unused")
public abstract class Conversation {
    private final HumanEntity player;

    public Conversation(HumanEntity player) {
        this.player = player;
    }

    public abstract void conversation() throws InterruptedException, ExecutionException, TimeoutException;

    public String getPlayerInput() throws ExecutionException, InterruptedException {
        return AirGameUtils.getPlayerInput(player).get();
    }

    public String getPlayerInput(long timeLimitBySecond) throws InterruptedException, ExecutionException, TimeoutException {
        return AirGameUtils.getPlayerInput(player).get(timeLimitBySecond, TimeUnit.SECONDS);
    }

    public String getPlayerInput(long timeLimit, TimeUnit limitUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return AirGameUtils.getPlayerInput(player).get(timeLimit, limitUnit);
    }

    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    public void sendMessage(String message, Object... args) {
        sendMessage(String.format(message, args));
    }

    public void onInterruptedException(InterruptedException e) {
    }

    public void onExecutionException(ExecutionException e) {
    }

    public void onTimeoutException(TimeoutException e) {
    }

    public void onException(Exception e) {
    }

    public BukkitTask start(Plugin plugin) {
        AirGamePlugin.sync(player::closeInventory);
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                conversation();
            } catch (InterruptedException e) {
                onInterruptedException(e);
                onException(e);
                AirGamePlugin.getLogUtils().debug(e, "执行会话时遇到了一个中断异常: ");
            } catch (ExecutionException e) {
                onExecutionException(e);
                onException(e);
                AirGamePlugin.getLogUtils().debug(e, "执行会话时遇到了一个执行异常: ");
            } catch (TimeoutException e) {
                onTimeoutException(e);
                onException(e);
                AirGamePlugin.getLogUtils().debug(e, "执行会话时遇到了一个超时异常: ");
            } catch (Exception e) {
                onException(e);
                AirGamePlugin.getLogUtils().debug(e, "执行会话时遇到了一个意料之外的异常: ");
            }
        });
    }

    public HumanEntity getPlayer() {
        return player;
    }
}
